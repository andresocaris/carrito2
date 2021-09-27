package com.ao.juego.service;

import com.ao.juego.controller.dto.ProductosCantidadDto;
import com.ao.juego.model.ProductoVenta;

public interface ProductoVentaService {
	ProductoVenta crearProductoVenta(ProductoVenta productoVenta);

	void crearRegistrosProductoVenta(Integer idVenta, ProductosCantidadDto productos);
}
