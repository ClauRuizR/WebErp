package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.Proveedor;
import com.weberp.app.dto.ProveedorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProveedorService {

	List<Proveedor> getProveedores();
	
	Proveedor getProveedorById(Long id);
	
	Proveedor guardar(Proveedor proveedor);
	List<Proveedor> buscarProveedor(ProveedorDTO proveedorDTO);

	Page<ProveedorDTO> findPaginated(int page, int size);

	Page<ProveedorDTO> findProveedorAndPaginated(String nombre, Pageable pageRequest);
}
