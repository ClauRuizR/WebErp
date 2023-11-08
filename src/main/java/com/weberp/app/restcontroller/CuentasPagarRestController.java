package com.weberp.app.restcontroller;

import com.weberp.app.common.view.RestControllerGeneric;
import com.weberp.app.dto.CuentasPagarDTO;
import com.weberp.app.services.CuentasPagarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by claudioruiz on 7/21/17.
 */
@RestController
@RequestMapping("/rest/cuentasPagar")
public class CuentasPagarRestController extends RestControllerGeneric<CuentasPagarDTO,Long> {


    @Autowired
    public CuentasPagarRestController(CuentasPagarService service) {
        super(service );
    }


}
