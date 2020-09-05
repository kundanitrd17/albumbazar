package com.albumbazaar.albumbazar.services;

import com.albumbazaar.albumbazar.dao.SuperuserRepository;
import com.albumbazaar.albumbazar.dao.principals.SuperuserPrincipal;
import com.albumbazaar.albumbazar.form.api.ForgotPasswordFormAPI;
import com.albumbazaar.albumbazar.model.Superuser;
import com.albumbazaar.albumbazar.services.api.SuperuserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
@Qualifier("superuserDetailsService")
public class SuperuserDetailsService implements UserDetailsService, SuperuserService{
    
    
    private final SuperuserRepository superuserRepo;

    @Autowired(required = true)
    public SuperuserDetailsService(final SuperuserRepository superuserRepository){
        this.superuserRepo = superuserRepository;
        System.out.println("Superuser details service");
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        
        final Superuser superuser = superuserRepo.findByEmail(username);
        if(superuser==null) {
            throw new UsernameNotFoundException("User 404");
        }

        return new SuperuserPrincipal(superuser);
    }



    @Override
    public ModelMap resetPassword(final ForgotPasswordFormAPI forgotPasswordForm) {
        System.out.println("Resetting password" + forgotPasswordForm);
        ModelMap modelMap = new ModelMap();
        if(!forgotPasswordForm.isValid()) {
            modelMap.addAttribute("status", HttpStatus.NOT_ACCEPTABLE);
            modelMap.addAttribute("message", "failed");
            return modelMap;
        }

        final var superuserPrincipal = (SuperuserPrincipal) SecurityContextHolder
                                                                            .getContext()
                                                                                .getAuthentication()
                                                                                    .getPrincipal();
        final var superuser = superuserPrincipal.getSuperuser();
        
        // superuser.setPassword(NoOpPasswordEncoder.getInstance().encode(superuser.getPassword()));
        superuser
            .setPassword(NoOpPasswordEncoder.getInstance()
                .encode(forgotPasswordForm.getPassword1())
            );

        superuserRepo.save(superuser);   
      
        modelMap.addAttribute("status", HttpStatus.OK);
        modelMap.addAttribute("message", "success");
        modelMap.addAttribute("data", superuser);

        return modelMap;
    }

}
