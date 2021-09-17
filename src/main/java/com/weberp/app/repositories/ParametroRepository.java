package com.weberp.app.repositories;

import com.weberp.app.domain.Parametro;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by claudioruiz on 5/31/17.
 */
public interface ParametroRepository extends CrudRepository<Parametro,Long>{

    Parametro findByCodigo(Long codigo);
}
