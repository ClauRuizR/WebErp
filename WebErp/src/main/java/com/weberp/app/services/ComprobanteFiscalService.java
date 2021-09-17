package com.weberp.app.services;

import java.text.ParseException;
import java.util.List;

import com.weberp.app.domain.ComprobanteFiscal;
import com.weberp.app.dto.ComprobanteFiscalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by claudioruiz on 8/14/16.
 */
public interface ComprobanteFiscalService  {
    List<ComprobanteFiscal> listaComprobanteFiscal();

    ComprobanteFiscalDTO guardar(ComprobanteFiscalDTO comprobanteFiscalDTO) throws ParseException;

    ComprobanteFiscalDTO getComprobanteFiscalById(Long id);

    void borrar(Long id);


    
    void incrementarComprobanteFiscal(Long id);





    Page<ComprobanteFiscalDTO> findPaginated(int page, int size);

    Page<ComprobanteFiscalDTO> findComprobanteFiscalDTOAndPaginated(String letra, Pageable pageRequest) throws ParseException;



}
