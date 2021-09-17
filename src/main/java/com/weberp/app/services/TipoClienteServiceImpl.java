package com.weberp.app.services;

import com.weberp.app.domain.TipoCliente;
import com.weberp.app.dto.TipoClienteDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.repositories.TipoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by claudioruiz on 7/6/17.
 */
@Service
public class TipoClienteServiceImpl extends ConfigMapper implements TipoClienteService {

    @Autowired
    private TipoClienteRepository tipoClienteRepository;


    @Override
    public List<TipoClienteDTO> listaTipoCliente() {

        List<TipoCliente> clientesList = tipoClienteRepository.findAll();
        return clientesList.stream()
                .map(tipoCliente -> convertTipoClienteToDto(tipoCliente))
                .collect(Collectors.toList());
    }

    @Override
    public TipoClienteDTO guardar(TipoClienteDTO tipoClienteDTO) {

        TipoCliente tipoCliente = null;
        try {
            tipoCliente = convertTipoClienteToEntity(tipoClienteDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tipoCliente = tipoClienteRepository.save(tipoCliente);
        return convertTipoClienteToDto(tipoCliente);
    }

    @Override
    public TipoClienteDTO getTipoClienteDTOById(Long id) {

        TipoCliente tipoCliente = tipoClienteRepository.findOne(id);


        return convertTipoClienteToDto(tipoCliente);
    }

    @Override
    public void borrar(Long id) {
        tipoClienteRepository.delete(id);
    }

    @Override
    public Page<TipoClienteDTO> findPaginated(int page, int size) {
        Page<TipoCliente> tipoClientePage=  (tipoClienteRepository.findAll(new PageRequest(page,size, Sort.Direction.ASC,"id")));

        final Page<TipoClienteDTO> tipoClienteDTOPage = tipoClientePage.map(this::convertTipoClienteToDto);
        return tipoClienteDTOPage;
    }

    @Override
    public Page<TipoClienteDTO> findTipoClienteAndPaginated(String codigo, Pageable pageRequest) throws ParseException {
        Page<TipoCliente> tipoClientePage=  (tipoClienteRepository.findByCodigo(codigo,pageRequest));

        final Page<TipoClienteDTO> tipoClienteDTOPage = tipoClientePage.map(this::convertTipoClienteToDto);
        return tipoClienteDTOPage;
    }
}
