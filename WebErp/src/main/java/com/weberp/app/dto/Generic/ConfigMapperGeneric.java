package com.weberp.app.dto.Generic;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.UUID;

/**
 * Created by claudioruiz on 7/22/17.
 */
public abstract class   ConfigMapperGeneric<T,D extends Serializable>   {

    @Autowired
    private ModelMapper modelMapper;




    public D convertToDto(T tSource, Class<D> tDestination) {
       D tDto = modelMapper.map(tSource,tDestination);

        return tDto;
    }


    public D convertToEntity(T tSource,Class<D> tDestination) throws ParseException {
        D tEntity = modelMapper.map(tSource, tDestination);

        return tEntity;
    }


}
