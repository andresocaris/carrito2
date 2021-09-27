package com.ao.juego.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ao.juego.controller.dto.ProductoDto;
import com.ao.juego.model.Producto;
import com.ao.juego.model.custom.ProductoCantidad;
import com.ao.juego.model.custom.ReporteProduct;
import com.ao.juego.model.custom.ReporteProducto;

public interface ProductoService {
	Page<Producto> obtenerProductosPaginas(int size, int pagina);

	Page<Producto> obtenerProductosPorCosto(Integer costo);

	List<Producto> obtenerProductos();

	List<ReporteProducto> reporteProductos();

	List<ProductoCantidad> obtenerProductosMasVendidosConPaginacion(int numeroPagina, int tamanoPagina);

	List<ReporteProducto> reporteProductosConPaginacion(int cantidadPagina, int numeroPagina);

	List<ReporteProduct> obtenerProductosPorCampos();

	Producto addProductoDto(ProductoDto productoDto);

	Producto editarProducto(Producto producto);

	Producto obtenerProductoPorNombre(String nombre);

	String obtenerNombreDelProductoPorId(int idProducto);
}
