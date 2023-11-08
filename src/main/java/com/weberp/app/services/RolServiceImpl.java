package com.weberp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.Rol;
import com.weberp.app.dto.RolDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.repositories.RolRepository;

/**
 * Created by claudioruiz on 6/9/17.
 */
@Service
public class RolServiceImpl  extends ConfigMapper implements RolService {

    @Autowired
    private RolRepository rolRepository;



    @Override
    public List<Rol> listaRol() {
        return (List<Rol>) rolRepository.findAll();
    }

    @Override
    public Rol guardar(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Rol getRolById(Long id) {
        return rolRepository.findOne(id);
    }

    @Override
    public void borrar(Long id) {

        rolRepository.delete(id);

    }

    @Override
    public Page<RolDTO> findPaginated(int page, int size) {


        Page<Rol> rolPage=  (rolRepository.findAll(new PageRequest(page,size)));


        final Page<RolDTO> contactDtoPage = rolPage.map(this::convertRolToDto);
        return contactDtoPage;
    }

    @Override
    public Page<RolDTO> findRolAndPaginated(String rol, Pageable pageRequest) {
        Page<Rol> rolPage=  rolRepository.findByRol(rol,pageRequest);

        final Page<RolDTO> contactDtoPage = rolPage.map(this::convertRolToDto);
        return contactDtoPage;
    }
}
