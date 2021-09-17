package com.weberp.app.common.model;

import com.weberp.app.services.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by claudioruiz on 6/8/17.
 */
public class UsuarioUtil {

    public static String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
    }

    public static UserDetailsImpl getCurrentUserEmpresa() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return ((UserDetailsImpl) authentication.getPrincipal());
    }
}

