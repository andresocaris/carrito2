package com.ao.juego.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ao.juego.controller.dto.ProductoDto;
import com.ao.juego.model.Categoria;
import com.ao.juego.model.Producto;
import com.ao.juego.model.custom.ReporteProduct;
import com.ao.juego.model.custom.ReporteProducto;
import com.ao.juego.repository.CategoriaRepo;
import com.ao.juego.repository.ProductoRepo;
import com.ao.juego.util.FuncionUtil;

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
		return productoRepo.mostrarProductos();
	}

	@Override
	public Producto editarProducto(Producto producto) {
		Producto productoEditado= productoRepo.findProductoById(producto.getId());	
		if (productoEditado==null)return null;
		FuncionUtil util = new FuncionUtil();
		BeanUtils.copyProperties(producto, productoEditado, util.getNullPropertyNames(producto));
		return productoRepo.save(productoEditado);
	}

	@Override
	public Producto obtenerProductoPorName(String nombre) {
		return productoRepo.findProductoByNombre(nombre);
	}

}
