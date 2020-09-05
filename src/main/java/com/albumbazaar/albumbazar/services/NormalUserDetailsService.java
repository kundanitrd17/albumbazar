package com.albumbazaar.albumbazar.services;

import com.albumbazaar.albumbazar.dao.SuperuserRepository;
import com.albumbazaar.albumbazar.dao.principals.UserPrincipal;
import com.albumbazaar.albumbazar.model.Superuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class NormalUserDetailsService implements UserDetailsService {

    final private SuperuserRepository userRepo;

    @Autowired
    public NormalUserDetailsService(final SuperuserRepository userRepo) {
        this.userRepo = userRepo;
        
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        System.out.println("In user service");
        Superuser user = userRepo.findByEmail(username);
        if(user==null) {
            throw new UsernameNotFoundException("User 404");
        }

        return new UserPrincipal(user);
    }
}
