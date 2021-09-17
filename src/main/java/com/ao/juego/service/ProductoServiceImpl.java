package com.ao.juego.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ao.juego.controller.dto.ProductoDto;
import com.ao.juego.model.Categoria;
import com.ao.juego.model.Producto;
import com.ao.juego.model.custom.ReporteProduct;
import com.ao.juego.model.custom.ReporteProducto;
import com.ao.juego.repository.CategoriaRepo;
import com.ao.juego.repository.ProductoRepo;

@Service
public class ProductoServiceImpl implements ProductoService {

	private final ProductoRepo productoRepo;
	private final CategoriaRepo categoriaRepo;

	public ProductoServiceImpl(ProductoRepo productoRepo, CategoriaRepo categoriaRepo) {
		this.productoRepo = productoRepo;
		this.categoriaRepo = categoriaRepo;
	}

	@Override
	public List<Producto> obtenerProductos() {
		return productoRepo.findAll();
	}

	@Override
	public List<ReporteProducto> reporteProductos() {
		// TODO Auto-generated method stub
		return productoRepo.reporteProductos();

	}

	@Override
	public Producto addProducto(ProductoDto productoDto) {
		Producto producto = new Producto();
		Categoria categoria = categoriaRepo.findByNombre(productoDto.getCategoria());
		if ( categoria == null ) return null;
		Integer idCategoria=categoria.getId();
		producto.setNombre(productoDto.getNombre());
		producto.setCosto(productoDto.getCosto());
		producto.setIdCategoria(idCategoria);
		productoRepo.save(producto);
		return producto;
	}

	@Override
	public List<ReporteProduct> obtenerProductosPorCampos() {
		//return null;
		return productoRepo.mostrarProductos();
	}

}
