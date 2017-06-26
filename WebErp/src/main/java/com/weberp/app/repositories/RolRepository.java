package com.weberp.app.repositories;

import com.weberp.app.domain.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by claudioruiz on 6/8/17.
 */


public interface RolRepository extends JpaRepository<Rol,Long> {

    Rol findByRol(String role);


    Page<Rol> findByRol(String rol, Pageable pageCriteira);
}
