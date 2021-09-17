package com.weberp.app.services;

import com.weberp.app.domain.CuentasPagar;
import com.weberp.app.dto.CuentasPagarDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.repositories.CuentasPagarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by claudioruiz on 7/21/17.
 */

@Service
public class CuentasPagarServiceImpl extends ConfigMapper implements CuentasPagarService {

    @Autowired
    private CuentasPagarRepository cuentasPagarRepository;

    @Override
    public List<CuentasPagarDTO> findAll() {
        List<CuentasPagar> cuentasPagarList = cuentasPagarRepository.findAll();
        return cuentasPagarList.stream()
                .map(cuentasPagar -> convertCuentasPagarToDto(cuentasPagar))
                .collect(Collectors.toList());
    }

    @Override
    public CuentasPagarDTO Add(CuentasPagarDTO cuentasPagarDTO) throws ParseException {

        CuentasPagar cuentasPagar = convertCuentasPagarToEntity(cuentasPagarDTO);
        cuentasPagar= cuentasPagarRepository.save(cuentasPagar);
        return convertCuentasPagarToDto(cuentasPagar);
    }

    @Override
    public CuentasPagarDTO findOne(Long id) {
        return convertCuentasPagarToDto(cuentasPagarRepository.findOne(id));
    }

    @Override
    public void delete(Long id) {

        cuentasPagarRepository.delete(id);

    }

    @Override
    public Page<CuentasPagarDTO> findPaginated(int page, int size, Sort sort) {
        Page<CuentasPagar> cuentasPagarPage = cuentasPagarRepository.findAll(new PageRequest(page,size, sort));
        final Page<CuentasPagarDTO> cuentasPagarDTOPage = cuentasPagarPage.map(this::convertCuentasPagarToDto);
        return cuentasPagarDTOPage;
    }
}
