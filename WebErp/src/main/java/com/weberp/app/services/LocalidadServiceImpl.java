package com.weberp.app.services;

import com.weberp.app.domain.Localidad;
import com.weberp.app.repositories.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.List;

/**
 * Created by claudioruiz on 5/29/17.
 */
@Service
public class LocalidadServiceImpl implements LocalidadService {


    @Autowired
    private LocalidadRepository localidadRepository;
    @Override
    public List<Localidad> listaLocalidades() {
        return (List<Localidad>) localidadRepository.findAll();
    }

    @Override
    public Localidad guardar(Localidad localidad) {
        return localidadRepository.save(localidad);
    }

    @Override
    public Localidad getLocalidadById(Long id) {
        return localidadRepository.findOne(id);
    }
}
