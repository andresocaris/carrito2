package com.ao.juego.model.custom;

public class ProductoCantidad {
	private int idProducto;
	private long cantidad;
	
	public ProductoCantidad(int idProducto, long cantidad) {
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	} 
}
