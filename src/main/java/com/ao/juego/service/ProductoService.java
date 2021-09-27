package com.ao.juego.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ao.juego.controller.dto.ProductoDto;
import com.ao.juego.model.Producto;
import com.ao.juego.model.custom.ProductoCantidad;
import com.ao.juego.model.custom.ProductoConNombre;
import com.ao.juego.model.custom.ProductoPorIdYNombre;

public interface ProductoService {
	Page<Producto> obtenerPageProductosConPaginacion(int size, int pagina);

	Page<Producto> obtenerPageProductosPorCostoConPaginacion(Integer costo, int numeroPagina, int tamanoPagina);

	List<ProductoCantidad> obtenerProductosMasVendidosConPaginacion(int numeroPagina, int tamanoPagina);

	List<ProductoPorIdYNombre> reporteProductosConPaginacion(int cantidadPagina, int numeroPagina);

	List<Producto> obtenerProductos();

	List<ProductoPorIdYNombre> obtenerReporteProductos();

	List<ProductoConNombre> obtenerProductosPorCampos();

	Producto addProductoPorDto(ProductoDto productoDto);

	Producto editarProducto(Producto producto);

	Producto obtenerProductoPorNombre(String nombre);

	String obtenerNombreDelProductoPorId(int idProducto);
}
