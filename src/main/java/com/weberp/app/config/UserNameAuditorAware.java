package com.weberp.app.config;

import org.apache.log4j.Logger;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.weberp.app.bootstrap.ClienteLoader;
import com.weberp.app.services.UserDetailsImpl;



public class UserNameAuditorAware implements AuditorAware<String>{
	
	private Logger log = Logger.getLogger(UserNameAuditorAware.class);
	
	@Override
    public String getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        log.info("Usuario " + ((UserDetailsImpl) authentication.getPrincipal()).getUsername());
          return ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
    }
}

