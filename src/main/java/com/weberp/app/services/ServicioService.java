package com.weberp.app.services;

import com.weberp.app.domain.Servicio;
import com.weberp.app.dto.ServicioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by claudioruiz on 6/12/17.
 */
public interface ServicioService {
    List<Servicio> listaServicio();

    Servicio guardar(Servicio servicio);

    Servicio getServicioById(Long id);


    Page<ServicioDTO> findPaginated(int page, int size);

    Page<ServicioDTO> findServicioandPaginated(String nombre, Pageable pageRequest);

}
