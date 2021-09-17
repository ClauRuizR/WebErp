package com.weberp.app.dto;

public class TransferenciaAlmacen {

	private Long almacenOrigen;

	private Long productoId;

	private Long cantidad;

	private Long almacenDestino;

	public Long getAlmacenOrigen() {
		return almacenOrigen;
	}

	public void setAlmacenOrigen(Long almacenOrigen) {
		this.almacenOrigen = almacenOrigen;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public Long getAlmacenDestino() {
		return almacenDestino;
	}

	public void setAlmacenDestino(Long almacenDestino) {
		this.almacenDestino = almacenDestino;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

}
