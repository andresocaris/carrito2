package com.ao.juego.model.custom;

public class ReporteProduct {
	private String nombre;
	private Integer costo;
	private String nombreCategoria;
	
	public ReporteProduct(String nombre, Integer costo, String nombreCategoria) {
		this.nombre = nombre;
		this.costo = costo;
		this.nombreCategoria = nombreCategoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCosto() {
		return costo;
	}
	public void setCosto(Integer costo) {
		this.costo = costo;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
}
