package com.weberp.app.services;

import com.weberp.app.domain.GrupoEmpresa;
import com.weberp.app.dto.GrupoEmpresaDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.repositories.GrupoEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by claudioruiz on 6/8/17.
 */
@Service
public class GrupoEmpresaServiceImpl extends ConfigMapper implements GrupoEmpresaService {

    @Autowired
    private GrupoEmpresaRepository grupoEmpresaRepository;

    @Override
    public List<GrupoEmpresa> listaGrupoEmpresa() {
        return (List<GrupoEmpresa>) grupoEmpresaRepository.findAll();
    }

    @Override
    public GrupoEmpresa guardar(GrupoEmpresa grupoEmpresa) {
        return grupoEmpresaRepository.save(grupoEmpresa);
    }

    @Override
    public GrupoEmpresa getGrupoEmpresaById(Long id) {
        return grupoEmpresaRepository.findOne(id);
    }

    @Override
    public void borrar(Long id) {
        grupoEmpresaRepository.delete(id);

    }

    @Override
    public Page<GrupoEmpresaDTO> findPaginated(int page, int size) {

        Page<GrupoEmpresa> grupoEmpresaPage=  (grupoEmpresaRepository.findAll(new PageRequest(page,size)));

        final Page<GrupoEmpresaDTO> contactDtoPage = grupoEmpresaPage.map(this::convertToDtoGrupoEmpresa);
        return contactDtoPage;
    }

    @Override
    public Page<GrupoEmpresaDTO> findGrupoEmpresaAndPaginated(String nombre, Pageable pageRequest) {
        Page<GrupoEmpresa> grupoEmpresaPage=  grupoEmpresaRepository.findByNombre(nombre,pageRequest);

        final Page<GrupoEmpresaDTO> contactDtoPage = grupoEmpresaPage.map(this::convertToDtoGrupoEmpresa);
        return contactDtoPage;
    }
}
