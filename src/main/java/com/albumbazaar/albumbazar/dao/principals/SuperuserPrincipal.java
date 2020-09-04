package com.albumbazaar.albumbazar.dao.principals;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import com.albumbazaar.albumbazar.model.Superuser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SuperuserPrincipal implements UserDetails {

    private Superuser superuser;

    public SuperuserPrincipal(Superuser superuser) {
        this.superuser = superuser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(superuser.getRole());
        return Collections.singleton(new SimpleGrantedAuthority(superuser.getRole()));
    }
    @Override
    public String getUsername() {
        return superuser.getEmail();
    }
    @Override
    public String getPassword() {
        return superuser.getPassword();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
