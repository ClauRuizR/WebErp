package com.weberp.app.services;

import java.util.List;

import com.weberp.app.domain.Factura;
import com.weberp.app.dto.FacturaDTO;
import com.weberp.app.model.TipoFactura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by claudioruiz on 7/27/16.
 */
public interface FacturaService {

	Page<FacturaDTO> listaFacturas(Pageable pageRequest);

	Factura guardar(Factura factura);

	Factura getFacturaById(Long id);

	void borrar(Long id);

	List<TipoFactura> listaTipoFacturas();



	boolean generaSalidaProductos(Factura factura);

	void crearRegistroEnDiarioGeneral(Factura factura);

	Factura cambiarEstatusFactura(Factura factura);

	boolean validEstatusActualFactura(Long id, String estatus);

	List<Factura> facturasPorCobrar();


	Page<FacturaDTO> findPaginated(int page, int size);

	Page<FacturaDTO> findFacturaAndPaginated(String numeroDocumento, Pageable pageRequest);

}
