package com.weberp.app.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weberp.app.common.view.BaseRestController;
import com.weberp.app.domain.TipoDocumento;
import com.weberp.app.repositories.TipoDocumentoRepository;
import com.weberp.app.services.TipoDocumentoService;

/**
 * Created by claudioruiz on 6/6/17.
 */

@RestController
@RequestMapping("/rest/tipoDocumento")
public class TipoDocumentoRestController extends BaseRestController<TipoDocumento, Long> {

    @Autowired
    public TipoDocumentoRestController(TipoDocumentoRepository repo) {
        super(repo);
    }


    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @RequestMapping(value="documentosSalida",method = RequestMethod.GET)
    @ResponseBody
    public List<TipoDocumento> getDocumentosSalida(){

    return tipoDocumentoService.listaTiposDocumentosSalida();
    }
}
