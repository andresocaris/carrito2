package com.ao.juego.service;

import java.util.List;

import com.ao.juego.controller.dto.ProductoDto;
import com.ao.juego.model.Producto;
import com.ao.juego.model.custom.ReporteProduct;
import com.ao.juego.model.custom.ReporteProducto;

public interface ProductoService {
	List<Producto> obtenerProductos();
	Producto addProducto(ProductoDto productoDto);
	List<ReporteProducto> reporteProductos();
	List<ReporteProduct> obtenerProductosPorCampos();
	Producto editarProducto(Producto producto);

}
