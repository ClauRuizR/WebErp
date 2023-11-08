package com.weberp.app.restcontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weberp.app.domain.GrupoEmpresa;
import com.weberp.app.dto.GrupoEmpresaDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.GrupoEmpresaService;

/**
 * Created by claudioruiz on 6/9/17.
 */

@RestController
@RequestMapping("/rest/seguridad")
public class SeguridadRestController  extends  ConfigMapper {

    @Autowired
    private GrupoEmpresaService grupoEmpresaService;

    @RequestMapping(value="/consultaGrupoEmpresa",method = RequestMethod.GET)
    @ResponseBody
    public List<GrupoEmpresaDTO> getCliente() {
        //...
        List<GrupoEmpresa> listaGrupoEmpresa = grupoEmpresaService.listaGrupoEmpresa();
        return listaGrupoEmpresa.stream()
                .map(grupoEmpresa -> convertToDtoGrupoEmpresa(grupoEmpresa))
                .collect(Collectors.toList());
    }






}
