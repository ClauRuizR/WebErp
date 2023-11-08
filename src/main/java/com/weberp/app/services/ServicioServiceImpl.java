package com.weberp.app.services;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.domain.Empresa;
import com.weberp.app.domain.Servicio;
import com.weberp.app.dto.ServicioDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.repositories.ServicioRepository;
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
public class ServicioServiceImpl extends ConfigMapper implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public List<Servicio> listaServicio() {
        return (List<Servicio>) servicioRepository.findAll();
    }

    @Override
    public Servicio guardar(Servicio servicio) {

        Empresa empresa = UsuarioUtil.getCurrentUserEmpresa().getEmpresa();

        servicio.setEmpresa(empresa);
        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio getServicioById(Long id) {
        return servicioRepository.findOne(id);
    }

    @Override
    public Page<ServicioDTO> findPaginated(int page, int size) {

        Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();
        Page<Servicio> servicioPage=  (servicioRepository.findByEmpresa_Id(empresaId,new  PageRequest(page,size)));

        final Page<ServicioDTO> contactDtoPage = servicioPage.map(this::convertServicioToDto);
        return contactDtoPage;
    }

    @Override
    public Page<ServicioDTO> findServicioandPaginated(String nombre, Pageable pageRequest) {

        Page<Servicio> servicioPage=  servicioRepository.findByNombre(nombre,pageRequest);

        final Page<ServicioDTO> contactDtoPage = servicioPage.map(this::convertServicioToDto);
        return contactDtoPage;
    }
}
