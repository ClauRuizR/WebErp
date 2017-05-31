package com.weberp.app.model;



import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



/**
 * 
 * @author CarlosMarte
 *
 */

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractEntity {
	
	@CreatedDate
    private Date creadoEn;
	
    @CreatedBy
    private String creadoPor;
    
    @LastModifiedDate
    private Date modificadoEn;
    
    @LastModifiedBy
    private String modificadoPor;
    
    



}