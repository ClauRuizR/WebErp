package com.weberp.app.services;

import com.weberp.app.domain.Empresa;
import com.weberp.app.dto.EmpresaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by claudioruiz on 6/8/17.
 */
public interface EmpresaService {

    List<Empresa> listaEmpresa();

    Empresa guardar(Empresa empresa);

    Empresa getEmpresaById(Long id);

    void borrar(Long id);

    List<Empresa> listaEmpresaPorUsuario();




    Page<EmpresaDTO> findPaginated(int page, int size);

    Page<EmpresaDTO> findEmpresaAndPaginated(String nombre, Pageable pageRequest);

}
