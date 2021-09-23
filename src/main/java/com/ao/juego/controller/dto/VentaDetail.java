package com.ao.juego.controller.dto;

import java.util.Date;

public class VentaDetail {
	private Integer montoVenta;
	private Date date;
	private String nombreUsuario;
	public Integer getMontoVenta() {
		return montoVenta;
	}
	public void setMontoVenta(Integer montoVenta) {
		this.montoVenta = montoVenta;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
}
