package com.ao.juego.controller.dto;

import java.util.LinkedList;
import java.util.List;

public class ProductosCantidadDto {
	private List<ProductoCantidadDetailDto> productos;
	
	public ProductosCantidadDto(){
		productos= new LinkedList<>();
	}
	public void add( ProductoCantidadDetailDto productoCantidadDetailDto) {
		productos.add(productoCantidadDetailDto);
	}
	public List<ProductoCantidadDetailDto> getProductos() {
		return productos;
	}
	

}
