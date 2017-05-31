package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.Proveedor;
import com.weberp.app.dto.ProveedorDTO;

public interface ProveedorService {

	List<Proveedor> getProveedores();
	
	Proveedor getProveedorById(Long id);
	
	Proveedor guardar(Proveedor proveedor);
	List<Proveedor> buscarProveedor(ProveedorDTO proveedorDTO);
}
