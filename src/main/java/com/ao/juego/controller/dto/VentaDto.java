package com.ao.juego.controller.dto;

public class VentaDto {
	private ProductosCantidadDto productosCantidadDto;
	private VentaDetail ventaDetail;
	public ProductosCantidadDto getProductosCantidadDto() {
		return productosCantidadDto;
	}
	public void setProductosCantidadDto(ProductosCantidadDto productosCantidadDto) {
		this.productosCantidadDto = productosCantidadDto;
	}
	public VentaDetail getVentaDetail() {
		return ventaDetail;
	}
	public void setVentaDetail(VentaDetail ventaDetail) {
		this.ventaDetail = ventaDetail;
	}
}
