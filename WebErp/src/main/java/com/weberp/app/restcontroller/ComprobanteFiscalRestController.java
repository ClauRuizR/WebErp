package com.weberp.app.restcontroller;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weberp.app.services.ComprobanteFiscalService;


/**
 * Created by claudioruiz on 8/14/16.
 */
@RequestMapping(value="/rest/comprobantefiscal")
@RestController
public class ComprobanteFiscalRestController {

    private ComprobanteFiscalService comprobanteFiscalService;


    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    public ComprobanteFiscalRestController(ComprobanteFiscalService comprobanteFiscalService) {
        this.comprobanteFiscalService = comprobanteFiscalService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ComprobanteDto obtenerComprobanteFiscal(){

        Long empresaId = usuarioService.findByUsuario(UsuarioUtil.getCurrentUser()).getEmpresa().getId();

        String comprobantes = comprobanteFiscalService.obtenerComprobanteFiscal(empresaId);
        ComprobanteDto comprobanteDto = new ComprobanteDto();
        comprobanteDto.setComprobante(comprobantes);
        return comprobanteDto;

    }

   public class  ComprobanteDto {

        private String comprobante;


      public String getComprobante() {
          return comprobante;
      }

      public void setComprobante(String comprobante) {
          this.comprobante = comprobante;
      }
  }

}
