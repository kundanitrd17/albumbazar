package com.albumbazaar.albumbazar.services.api;

import org.springframework.ui.ModelMap;

public interface SuperuserService<T> {
    
    public abstract ModelMap resetPassword(T FormData);
}
 