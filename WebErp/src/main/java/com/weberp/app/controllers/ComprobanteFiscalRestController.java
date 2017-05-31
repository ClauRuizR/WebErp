package com.weberp.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weberp.app.services.ComprobanteFiscalService;


/**
 * Created by claudioruiz on 8/14/16.
 */
@RequestMapping(value="/comprobantefiscal")
@RestController
public class ComprobanteFiscalRestController {

    private ComprobanteFiscalService comprobanteFiscalService;

    @Autowired
    public ComprobanteFiscalRestController(ComprobanteFiscalService comprobanteFiscalService) {
        this.comprobanteFiscalService = comprobanteFiscalService;
    }

    @RequestMapping(value = "obtenerComprobanteFiscal",method = RequestMethod.GET)
    public String obtenerComprobanteFiscal(){

        return comprobanteFiscalService.obtenerComprobanteFiscal();

    }



}
