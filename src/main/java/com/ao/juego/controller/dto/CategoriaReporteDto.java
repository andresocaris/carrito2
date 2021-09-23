package com.ao.juego.controller.dto;

import java.util.LinkedList;
import java.util.List;

public class CategoriaReporteDto {
	
	private List<CategoriaReporteDetailDto> categorias;
	
	public CategoriaReporteDto() {
		categorias = new LinkedList<>();
	}
	public void add( CategoriaReporteDetailDto categoria) {
		categorias.add(categoria);
	}
	public List<CategoriaReporteDetailDto> getCategorias() {
		return categorias;
	}
	
}
