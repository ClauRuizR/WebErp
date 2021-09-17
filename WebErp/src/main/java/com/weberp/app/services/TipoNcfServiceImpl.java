package com.weberp.app.services;

import com.weberp.app.domain.TipoNcf;
import com.weberp.app.dto.TipoNcfDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.repositories.TipoNcfRepository;
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
public class TipoNcfServiceImpl extends ConfigMapper implements TipoNcfService{

    @Autowired
    private TipoNcfRepository tipoNcfRepository;

    @Override
    public List<TipoNcfDTO> listaTipoDTONcf() {

        List<TipoNcf> tipoNcfList = tipoNcfRepository.findAll();
        return tipoNcfList.stream()
                .map(tipoNcf -> convertTipoNcfToDto(tipoNcf))
                .collect(Collectors.toList());
    }

    @Override
    public TipoNcfDTO guardar(TipoNcfDTO tipoNcfDTO) {
        TipoNcf tipoNcf = null;
        try {
             tipoNcf = convertTipoNcfToEntity(tipoNcfDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tipoNcf = tipoNcfRepository.save(tipoNcf);
        return convertTipoNcfToDto(tipoNcf) ;
    }

    @Override
    public TipoNcfDTO getTipoNcfById(Long id) {

        TipoNcf tipoNcf = tipoNcfRepository.findOne(id);
        return convertTipoNcfToDto(tipoNcf);
    }

    @Override
    public void borrar(Long id) {

        tipoNcfRepository.delete(id);
    }

    @Override
    public Page<TipoNcfDTO> findPaginated(int page, int size) {
        Page<TipoNcf> tipoNcfPage=  (tipoNcfRepository.findAll(new PageRequest(page,size, Sort.Direction.ASC,"id")));

        final Page<TipoNcfDTO> tipoNcfDTOPage = tipoNcfPage.map(this::convertTipoNcfToDto);
        return tipoNcfDTOPage;
    }

    @Override
    public Page<TipoNcfDTO> findTipoNcfDTOAndPaginated(String codigo, Pageable pageRequest) throws ParseException {
        Page<TipoNcf> tipoNcfPage=  (tipoNcfRepository.findByCodigo(codigo,pageRequest));

        final Page<TipoNcfDTO> tipoNcfDTOPage = tipoNcfPage.map(this::convertTipoNcfToDto);
        return tipoNcfDTOPage;
    }
}
