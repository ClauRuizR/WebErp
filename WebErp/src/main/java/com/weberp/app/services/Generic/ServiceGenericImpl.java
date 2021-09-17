package com.weberp.app.services.Generic;

import com.weberp.app.utils.JasperReportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by claudioruiz on 7/21/17.
 */
public class ServiceGenericImpl<S,ID extends Serializable> implements ServiceGeneric<S,ID>  {


    protected Class<S> entityDtoClass;

    @Autowired
    private JpaRepository<S, ID> repository;


    public ServiceGenericImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityDtoClass = (Class<S>) genericSuperclass
                .getActualTypeArguments()[0];
    }


    @Override
    public List<S> findAll() {
        return repository.findAll();
    }

    @Override
    public S Add(S t) {
        return repository.save(t);
    }

    @Override
    public S findOne(ID id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(ID id) {
        repository.delete(id);
    }

    @Override
    public Page<S> findPaginated(int page, int size,Sort sort) {
        return repository.findAll(new PageRequest(page,size,sort));
    }
}
