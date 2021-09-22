package com.ao.juego.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ao.juego.controller.dto.ProductoDto;
import com.ao.juego.model.Producto;
import com.ao.juego.model.custom.ReporteProduct;
import com.ao.juego.model.custom.ReporteProducto;

public interface ProductoService {
	Page<Producto> obtenerProductosPaginas(int size,int pagina);
	List<Producto> obtenerProductos();
	Producto addProducto(ProductoDto productoDto);
	List<ReporteProducto> reporteProductos();
	List<ReporteProduct> obtenerProductosPorCampos();
	Producto editarProducto(Producto producto);
	Producto obtenerProductoPorName(String nombre);
//	List<ReporteProducto> listarPaginado(Integer cantidadPagina, Integer numeroPagina);
//	List<ReporteProducto> listarPaginado(int cantidadPagina, int numeroPagina);
	
	Page<Producto> obtenerProductosPorCosto(Integer costo);

}
