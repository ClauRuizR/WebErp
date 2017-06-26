package com.weberp.app.restcontroller;

import com.weberp.app.domain.GrupoEmpresa;
import com.weberp.app.dto.GrupoEmpresaDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.EmpresaService;
import com.weberp.app.services.GrupoEmpresaService;
import com.weberp.app.services.RolService;
import org.apache.poi.ss.formula.functions.T;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by claudioruiz on 6/9/17.
 */

@RestController
@RequestMapping("/rest/seguridad")
public class SeguridadRestController  extends  ConfigMapper {

    @Autowired
    private GrupoEmpresaService grupoEmpresaService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private RolService rolService;

    @Autowired
    private ModelMapper modelMapper;



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
