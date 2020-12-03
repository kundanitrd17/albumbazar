package com.albumbazaar.albumbazar.model;

import org.springframework.security.core.GrantedAuthority;

public enum AvailableRoles implements GrantedAuthority {

    SUPERUSER(Code.SUPERUSER), CUSTOMER_CARE(Code.CUSTOMER_CARE), USER(Code.USER), BRANCH(Code.BRANCH);

    private final String authority;

    AvailableRoles(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public class Code {
        public static final String SUPERUSER = "ROLE_SUPERUSER";
        public static final String CUSTOMER_CARE = "ROLE_CUSTOMER_CARE";
        public static final String USER = "ROLE_USER";
        public static final String BRANCH = "ROLE_BRANCH";
    }

}
