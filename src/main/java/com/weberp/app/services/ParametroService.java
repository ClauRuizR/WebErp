package com.weberp.app.services;

import com.weberp.app.domain.Parametro;

import java.util.List;

/**
 * Created by claudioruiz on 5/31/17.
 */
public interface ParametroService {


    List<Parametro> listaParametros();

    Parametro guardar(Parametro parametro);

    Parametro getParametroById(Long id);

    Parametro findByCodigo(Long codigo);
}
