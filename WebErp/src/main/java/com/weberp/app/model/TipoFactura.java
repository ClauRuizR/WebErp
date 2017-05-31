package com.weberp.app.model;

/**
 * Created by claudioruiz on 8/14/16.
 */
public class TipoFactura {

    public  TipoFactura(Long id, String nombre){
        this.nombre = nombre;
        this.id=id;
    }

    private Long id;
    private String nombre;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
