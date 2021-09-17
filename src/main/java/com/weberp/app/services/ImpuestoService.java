package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.Impuesto;

public interface ImpuestoService {

	List<Impuesto> listaImpuesto();

	Impuesto guardar(Impuesto impuesto);

	Impuesto getImpuestoById(Long id);

	void borrar(Long id);
	

	Impuesto findByLlaveAndEstado(String llave, int estado);
}
