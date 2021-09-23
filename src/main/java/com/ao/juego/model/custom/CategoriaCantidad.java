package com.ao.juego.model.custom;

public class CategoriaCantidad {
	private int idCategoria;
	private long cantidad;
	
	public CategoriaCantidad(int idCategoria, long cantidad) {
		this.idCategoria = idCategoria;
		this.cantidad = cantidad;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	
}
