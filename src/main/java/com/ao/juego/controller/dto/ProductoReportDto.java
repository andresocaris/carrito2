package com.ao.juego.controller.dto;

import java.util.LinkedList;
import java.util.List;

public class ProductoReportDto {
	private List<ProductoDetailDto> productos;

	public ProductoReportDto() {
		productos = new LinkedList<>();
	}

	public List<ProductoDetailDto> getProducts() {
		return productos;
	}

	public void add(ProductoDetailDto productoDetailDto) {
		productos.add(productoDetailDto);
	}
}
