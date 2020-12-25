package com.albumbazaar.albumbazar.dao.principals;

import java.util.Collection;
import java.util.Collections;

import com.albumbazaar.albumbazar.model.Customer;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomerPrincipal implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final Customer customer;
    private final String CUSTOMER_ROLE = "ROLE_CUSTOMER";

    public CustomerPrincipal(final Customer customer) {
        this.customer = customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority(CUSTOMER_ROLE));
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {

        return customer.getActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return customer.getActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return customer.getActive();
    }

    @Override
    public boolean isEnabled() {
        return customer.getActive();
    }

}
