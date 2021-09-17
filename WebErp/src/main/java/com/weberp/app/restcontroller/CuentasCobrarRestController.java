package com.weberp.app.restcontroller;

import com.weberp.app.common.view.RestControllerGeneric;
import com.weberp.app.dto.CuentasCobrarDTO;
import com.weberp.app.services.CuentasCobrarService;
import com.weberp.app.services.Generic.ServiceGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by claudioruiz on 7/22/17.
 */
@RestController
@RequestMapping("/rest/cuentasCobrar")
public class CuentasCobrarRestController extends RestControllerGeneric<CuentasCobrarDTO,Long> {

    @Autowired
    public CuentasCobrarRestController(CuentasCobrarService cuentasCobrarService) {
        super(cuentasCobrarService);
    }
}
