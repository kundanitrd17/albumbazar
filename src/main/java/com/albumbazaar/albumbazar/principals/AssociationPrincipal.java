package com.albumbazaar.albumbazar.principals;

import java.util.Collection;
import java.util.Collections;

import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.model.AvailableRoles;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AssociationPrincipal implements UserDetails {

    private static final long serialVersionUID = 1L;
    private final Association association;

    public AssociationPrincipal(final Association association) {

        this.association = association;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority(AvailableRoles.ASSOCIATION.toString()));
    }

    @Override
    public String getPassword() {
        return association.getPassword();
    }

    @Override
    public String getUsername() {
        return association.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return association.getActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return association.getActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return association.getActive();
    }

    @Override
    public boolean isEnabled() {
        return association.getActive();
    }

}
