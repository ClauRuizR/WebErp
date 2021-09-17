package com.weberp.app.services;

import com.weberp.app.domain.TipoServicio;
import com.weberp.app.dto.TipoServicioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by claudioruiz on 6/12/17.
 */
public interface TipoServicioService {
    List<TipoServicio> listaTipoServicio();

    TipoServicio guardar(TipoServicio tipoServicio);

    TipoServicio getTipoServicioId(Long id);

    TipoServicio findByTipoProducto_IdAndEmpresa_Id(Long tipoProductoId, Long empresaId);

    Page<TipoServicioDTO> findPaginated(int page, int size);

    Page<TipoServicioDTO> findTipoServicioAndPaginated(String nombre, Pageable pageRequest);

}
