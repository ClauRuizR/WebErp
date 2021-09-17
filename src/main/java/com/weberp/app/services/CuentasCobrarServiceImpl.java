package com.weberp.app.services;

import com.weberp.app.domain.CuentasCobrar;
import com.weberp.app.dto.CuentasCobrarDTO;
import com.weberp.app.dto.CuentasPagarDTO;
import com.weberp.app.dto.Generic.ConfigMapperGeneric;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.repositories.CuentasCobrarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by claudioruiz on 7/22/17.
 */

@Service
public class CuentasCobrarServiceImpl extends ConfigMapper implements CuentasCobrarService {

    @Autowired
    private CuentasCobrarRepository cuentasCobrarRepository;



    @Override
    public List<CuentasCobrarDTO> findAll() {


        List<CuentasCobrar> cuentasPagarList = cuentasCobrarRepository.findAll();
        return cuentasPagarList.stream()
                .map(cuentasPagar -> (convertCuentasCobrarToDto(cuentasPagar)))
                .collect(Collectors.toList());
    }

    @Override
    public CuentasCobrarDTO Add(CuentasCobrarDTO cuentasCobrarDTO) throws ParseException {
        return null;
    }

    @Override
    public CuentasCobrarDTO findOne(Long id) {
        CuentasCobrar cuentasCobrar = cuentasCobrarRepository.findOne(id);
        CuentasCobrarDTO cuentasCobrarDTO = convertCuentasCobrarToDto(cuentasCobrar);

        return cuentasCobrarDTO;
    }

    @Override
    public void delete(Long id) {
    cuentasCobrarRepository.delete(id);
    }

    @Override
    public Page<CuentasCobrarDTO> findPaginated(int page, int size, Sort sort) {

        Page<CuentasCobrar> cuentasPagarPage = cuentasCobrarRepository.findAll(new PageRequest(page,size, sort));
        final Page<CuentasCobrarDTO> cuentasPagarDTOPage = cuentasPagarPage.map(this::convertCuentasCobrarToDto);
        return cuentasPagarDTOPage;
    }
}
