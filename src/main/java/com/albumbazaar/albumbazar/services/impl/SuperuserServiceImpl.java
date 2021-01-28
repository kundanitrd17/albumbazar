package com.albumbazaar.albumbazar.services.impl;

import com.albumbazaar.albumbazar.dao.SuperuserRepository;
import com.albumbazaar.albumbazar.dao.WebsiteGeneralInfoRepository;
import com.albumbazaar.albumbazar.form.api.ForgotPasswordFormAPI;
import com.albumbazaar.albumbazar.model.Superuser;
import com.albumbazaar.albumbazar.model.WebsiteGeneralInfoEntity;
import com.albumbazaar.albumbazar.principals.SuperuserPrincipal;
import com.albumbazaar.albumbazar.services.SuperuserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

@Service
@Qualifier("superuserService")
public class SuperuserServiceImpl implements UserDetailsService, SuperuserService {

    private final SuperuserRepository superuserRepo;

    private final WebsiteGeneralInfoRepository websiteGeneralInfoRepository;

    @Autowired(required = true)
    public SuperuserServiceImpl(final SuperuserRepository superuserRepository,
            final WebsiteGeneralInfoRepository websiteGeneralInfoRepository) {
        this.superuserRepo = superuserRepository;
        this.websiteGeneralInfoRepository = websiteGeneralInfoRepository;

    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        final Superuser superuser = superuserRepo.findByEmail(username);
        if (superuser == null) {
            throw new UsernameNotFoundException("User 404");
        }

        return new SuperuserPrincipal(superuser);
    }

    @Override
    public ModelMap resetPassword(final ForgotPasswordFormAPI forgotPasswordForm) {
        System.out.println("Resetting password" + forgotPasswordForm);
        ModelMap modelMap = new ModelMap();
        if (!forgotPasswordForm.isValid()) {
            modelMap.addAttribute("status", HttpStatus.NOT_ACCEPTABLE);
            modelMap.addAttribute("message", "failed");
            return modelMap;
        }

        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof SuperuserPrincipal) {
            final SuperuserPrincipal superuserPrincipal = (SuperuserPrincipal) principal;
            final Superuser superuser = superuserRepo.findById(superuserPrincipal.getId()).orElseThrow();

            // superuser.setPassword(NoOpPasswordEncoder.getInstance().encode(superuser.getPassword()));
            superuser.setPassword(NoOpPasswordEncoder.getInstance().encode(forgotPasswordForm.getPassword1()));

            superuserRepo.save(superuser);

            modelMap.addAttribute("status", HttpStatus.OK);
            modelMap.addAttribute("message", "success");
            modelMap.addAttribute("data", superuser);

            return modelMap;
        } else {
            throw new RuntimeException("Unable to authenticate");
        }
    }

    @Override
    @Transactional
    public void updateGlobalDiscount(final double discount) {

        final WebsiteGeneralInfoEntity websiteGeneralInfoEntity = websiteGeneralInfoRepository.findById(1l)
                .orElseThrow();

        websiteGeneralInfoEntity.setDISCOUNT_FOR_ALL(discount);

    }

    @Override
    @Transactional
    public void updateReferallAmount(final double referralAmount) {
        final WebsiteGeneralInfoEntity websiteGeneralInfoEntity = websiteGeneralInfoRepository.findById(1l)
                .orElseThrow();

        websiteGeneralInfoEntity.setREFERALL_AMOUNT(referralAmount);

    }

    @Override
    public WebsiteGeneralInfoEntity getWebsiteInfoEntity() {
        return websiteGeneralInfoRepository.findById(1l).orElseThrow();
    }

}
