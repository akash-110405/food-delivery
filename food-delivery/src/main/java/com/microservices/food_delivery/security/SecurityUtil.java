package com.microservices.food_delivery.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static String getCurrentUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getAuthorities().isEmpty()) return null;

        return auth.getAuthorities().iterator().next().getAuthority();
        // returns: ROLE_ADMIN / ROLE_USER / ROLE_CHEF
    }
}
