package com.ntgschool.easystay.Security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityUtils {

    private void handelAuthNotFound(Authentication auth){
        if (auth == null ){
            throw new AccessDeniedException("Unauthenticated User" );
        }
        if (!auth.isAuthenticated()){
            throw new AccessDeniedException("Unauthenticated user with email " + auth.getName());
        }
    }

    public UUID getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        handelAuthNotFound(auth);

        UserPrincipal userDetails = (UserPrincipal) auth.getPrincipal();

        if (userDetails == null) {
            throw new AccessDeniedException("Unauthenticated User");
        }

        return userDetails.getId();
    }
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        handelAuthNotFound(auth);
        return auth.getName();
    }

    public UserPrincipal getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        handelAuthNotFound(auth);
        return (UserPrincipal) auth.getPrincipal();
    }
}
