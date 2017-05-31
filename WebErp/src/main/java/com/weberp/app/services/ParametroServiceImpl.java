package com.weberp.app.services;

import com.weberp.app.domain.Parametro;
import com.weberp.app.repositories.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by claudioruiz on 5/31/17.
 */
public class ParametroServiceImpl implements ParametroService {

    @Autowired
    private ParametroRepository parametroRepository;

    @Override
    public List<Parametro> listaParametros() {
        return (List<Parametro>) parametroRepository.findAll();
    }

    @Override
    public Parametro guardar(Parametro parametro) {
        return parametroRepository.save(parametro);
    }

    @Override
    public Parametro getParametroById(Long id) {
        return parametroRepository.findOne(id);
    }

    @Override
    public Parametro findByCodigo(Long codigo) {
        return parametroRepository.findByCodigo(codigo);
    }
}
