package com.albumbazaar.albumbazar.model;

import org.springframework.security.core.GrantedAuthority;

public enum AvailableRoles implements GrantedAuthority {

    SUPERUSER(Code.SUPERUSER), ASSOCIATION(Code.ASSOCIATION), CUSTOMER_CARE(Code.CUSTOMER_CARE), ADMIN(Code.ADMIN),
    USER(Code.USER), BRANCH(Code.BRANCH), DELIVERY(Code.DELIVERY);

    private final String authority;

    AvailableRoles(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return this.authority.toString();
    }

    public class Code {
        public static final String SUPERUSER = "ROLE_SUPERUSER";
        public static final String CUSTOMER_CARE = "ROLE_CUSTOMER_CARE";
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String BRANCH = "ROLE_BRANCH";
        public static final String ASSOCIATION = "ROLE_ASSOCIATION";
        public static final String DELIVERY = "ROLE_DELIVERY";
    }

}
