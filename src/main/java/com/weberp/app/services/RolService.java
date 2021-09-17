package com.weberp.app.services;

import com.weberp.app.domain.Rol;
import com.weberp.app.dto.RolDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by claudioruiz on 6/9/17.
 */
public interface RolService {
    List<Rol> listaRol();

    Rol guardar(Rol rol);

    Rol getRolById(Long id);

    void borrar(Long id);

    Page<RolDTO> findPaginated(int page, int size);

    Page<RolDTO> findRolAndPaginated(String rol, Pageable pageRequest);

}
