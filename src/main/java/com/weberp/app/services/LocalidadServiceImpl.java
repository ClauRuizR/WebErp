package com.weberp.app.services;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.domain.Empresa;
import com.weberp.app.domain.Localidad;
import com.weberp.app.dto.LocalidadDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.repositories.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by claudioruiz on 5/29/17.
 */
@Service
public class LocalidadServiceImpl extends ConfigMapper implements LocalidadService {


    @Autowired
    private LocalidadRepository localidadRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmpresaService empresaService;
    @Override
    public List<Localidad> listaLocalidades() {

        Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();

        return (List<Localidad>) localidadRepository.findByEmpresa_Id(empresaId);
    }

    @Override
    public Localidad guardar(Localidad localidad) {


        Empresa empresa = empresaService.getEmpresaById(localidad.getEmpresa().getId());
        localidad.setEmpresa(empresa);
        return localidadRepository.save(localidad);
    }

    @Override
    public Localidad getLocalidadById(Long id) {
        return localidadRepository.findOne(id);
    }

    @Override
    public List<Localidad> listaLocalidadesPorEmpresa() {

        Long empresaId = usuarioService.findByUsuario(UsuarioUtil.getCurrentUser()).getEmpresa().getId();

        return localidadRepository.findByEmpresa_Id(empresaId);
    }

    @Override
    public Page<LocalidadDTO> findPaginated(int page, int size) {
        Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();
        Page<Localidad> localidadPage=  (localidadRepository.findByEmpresa_Id(empresaId,new PageRequest(page,size, Sort.Direction.ASC,"id")));

        final Page<LocalidadDTO> contactDtoPage = localidadPage.map(this::convertLocalidadToDto);
        return contactDtoPage;
    }

    @Override
    public Page<LocalidadDTO> findLocalidadAndPaginated(String nombre, Pageable pageRequest) {
        Page<Localidad> localidadPage=  localidadRepository.findByNombre(nombre,pageRequest);

        final Page<LocalidadDTO> contactDtoPage = localidadPage.map(this::convertLocalidadToDto);
        return contactDtoPage;
    }

}
