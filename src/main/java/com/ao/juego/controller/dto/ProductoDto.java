package com.ao.juego.controller.dto;

public class ProductoDto {
	private String nombre;
	private Integer costo;
	private String categoria;
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "ProductoDto [nombre=" + nombre + ", costo=" + costo + ", categoria=" + categoria + "]";
	}
}
