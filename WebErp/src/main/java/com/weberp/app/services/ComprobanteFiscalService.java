package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.ComprobanteFiscal;

/**
 * Created by claudioruiz on 8/14/16.
 */
public interface ComprobanteFiscalService  {
    List<ComprobanteFiscal> listaComprobanteFiscal();

    ComprobanteFiscal guardar(ComprobanteFiscal comprobanteFiscal);

    ComprobanteFiscal getComprobanteFiscalById(Long id);

    void borrar(Long id);

    String obtenerComprobanteFiscal(Long empresaId);
    
    void incrementarComprobanteFiscal(Long id);


}
