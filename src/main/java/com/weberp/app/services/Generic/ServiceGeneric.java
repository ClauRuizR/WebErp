package com.weberp.app.services.Generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
