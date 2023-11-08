package com.weberp.app.common.view;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;

import com.weberp.app.services.UserDetailsImpl;
import com.weberp.app.services.UsuarioService;


public abstract class BaseController {


    @Autowired
    private UsuarioService usuarioService;

    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);


    public BaseController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void getRequestErrors(List<FieldError> errors) {
        for (FieldError error : errors) {
            logger.info(error.toString());
        }
    }

    public void setUsuario(Model model) {
        model.addAttribute("usuario", usuarioService.findByUsuario(getUser().getUsername()));

    }

    public UserDetailsImpl getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return ((UserDetailsImpl) authentication.getPrincipal());
    }





}
