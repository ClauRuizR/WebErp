package com.weberp.app.services;

import com.weberp.app.domain.Localidad;
import com.weberp.app.domain.TipoProducto;

import java.util.List;

/**
 * Created by claudioruiz on 5/29/17.
 */
public interface LocalidadService {
    List<Localidad> listaLocalidades();

    Localidad guardar(Localidad localidad);

    Localidad getLocalidadById(Long id);
}
