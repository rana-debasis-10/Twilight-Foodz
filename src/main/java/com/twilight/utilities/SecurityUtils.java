package com.twilight.utilities;

import com.twilight.components.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {}

    public static UserDetailsImpl currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof UserDetailsImpl)) {
            throw new UnauthorizedException("Unauthenticated");
        }
        return (CustomUserPrincipal) auth.getPrincipal();
    }

    public static Long userId() {
        return currentUser().getUserId();
    }

    public static String mobile() {
        return currentUser().getMobile();
    }

    public static String role() {
        return currentUser().getRole();
    }
}
