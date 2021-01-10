package com.albumbazaar.albumbazar.principals;

import java.util.Collection;
import java.util.Collections;

import com.albumbazaar.albumbazar.model.Employee;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class EmployeePrincipal implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Employee employee;

    public EmployeePrincipal(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(employee.getRole());
        return Collections.singleton(new SimpleGrantedAuthority(employee.getRole()));
    }

    public Long getId() {
        return employee.getId();
    }

    public String getRole() {
        return employee.getRole();
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return employee.getActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return employee.getActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return employee.getActive();
    }

    @Override
    public boolean isEnabled() {
        return employee.getActive();
    }
}
