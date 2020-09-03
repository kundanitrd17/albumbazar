package com.albumbazaar.albumbazar.services;

import com.albumbazaar.albumbazar.dao.SuperuserRepository;
import com.albumbazaar.albumbazar.dao.principals.SuperuserPrincipal;
import com.albumbazaar.albumbazar.model.Superuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SuperuserDetailsService implements UserDetailsService {
    
    
    private SuperuserRepository superuserRepo;

    @Autowired(required = true)
    public SuperuserDetailsService(SuperuserRepository superuserRepository){
        this.superuserRepo = superuserRepository;
        
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In Super service");
        Superuser superuser = superuserRepo.findByEmail(username);
        if(superuser==null) {
            throw new UsernameNotFoundException("User 404");
        }

        return new SuperuserPrincipal(superuser);
    }

}
