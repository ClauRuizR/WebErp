package com.weberp.app.services.Generic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

/**
 * Created by claudioruiz on 7/21/17.
 */
public interface  ServiceGeneric <S, ID extends Serializable> {

    List<S> findAll();

    S Add(S s) throws ParseException;

    S findOne(ID id);

    void delete(ID id);

    Page<S> findPaginated(int page, int size, Sort sort);


}
