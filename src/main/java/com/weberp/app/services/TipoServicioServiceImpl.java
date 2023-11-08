package com.weberp.app.services;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.domain.Empresa;
import com.weberp.app.domain.TipoServicio;
import com.weberp.app.dto.TipoServicioDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.repositories.TipoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by claudioruiz on 6/12/17.
 */
@Service
public class TipoServicioServiceImpl extends ConfigMapper implements TipoServicioService {

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    @Override
    public List<TipoServicio> listaTipoServicio() {
        Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();

        return (List<TipoServicio>) tipoServicioRepository.findByEmpresa_Id(empresaId);
    }

    @Override
    public TipoServicio guardar(TipoServicio tipoServicio) {

        Empresa empresa = UsuarioUtil.getCurrentUserEmpresa().getEmpresa();

        tipoServicio.setEmpresa(empresa);

        for (int i=0; i <tipoServicio.getDetalleTipoServicio().size();i++){
            tipoServicio.getDetalleTipoServicio().get(i).setTipoServicio(tipoServicio);
        }

        return tipoServicioRepository.save(tipoServicio);
    }

    @Override
    public TipoServicio getTipoServicioId(Long id) {
        return tipoServicioRepository.findOne(id);
    }

    @Override
    public TipoServicio findByTipoProducto_IdAndEmpresa_Id(Long tipoProductoId, Long empresaId) {
        return tipoServicioRepository.findByTipoProducto_IdAndEmpresa_Id(tipoProductoId,empresaId);
    }

    @Override
    public Page<TipoServicioDTO> findPaginated(int page, int size) {
        Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();

        Page<TipoServicio> tipoServicioPage=  (tipoServicioRepository.findByEmpresa_Id(empresaId,new PageRequest(page,size)));

        final Page<TipoServicioDTO> contactDtoPage = tipoServicioPage.map(this::convertTipoServicioToDto);
        return contactDtoPage;
    }

    @Override
    public Page<TipoServicioDTO> findTipoServicioAndPaginated(String nombre, Pageable pageRequest) {
        Page<TipoServicio> tipoServicioPage=  tipoServicioRepository.findByNombre(nombre,pageRequest);

        final Page<TipoServicioDTO> contactDtoPage = tipoServicioPage.map(this::convertTipoServicioToDto);
        return contactDtoPage;
    }
}
