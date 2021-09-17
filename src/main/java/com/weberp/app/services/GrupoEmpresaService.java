package com.weberp.app.services;

import com.weberp.app.domain.GrupoEmpresa;
import com.weberp.app.dto.GrupoEmpresaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by claudioruiz on 6/8/17.
 */
public interface GrupoEmpresaService {

    List<GrupoEmpresa> listaGrupoEmpresa();

    GrupoEmpresa guardar(GrupoEmpresa grupoEmpresa);

    GrupoEmpresa getGrupoEmpresaById(Long id);

    void borrar(Long id);



    Page<GrupoEmpresaDTO> findPaginated(int page, int size);

    Page<GrupoEmpresaDTO> findGrupoEmpresaAndPaginated(String nombre, Pageable pageRequest);

}
