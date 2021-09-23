package com.ao.juego.service;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ao.juego.controller.dto.ProductoDto;
import com.ao.juego.exceptions.CategoriaException;
import com.ao.juego.exceptions.ProductoException;
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
	public Page<Producto> obtenerProductosPaginas(int size, int pagina) {
		Pageable pag = PageRequest.of(pagina, size);
		
		return productoRepo.findAll(pag);
	}
	@Override
	public List<Producto> obtenerProductos() {
		// TODO Auto-generated method stub
		return productoRepo.findAll();
	}

	@Override
	public List<ReporteProducto> reporteProductos() {
		return productoRepo.reporteProductos();
	}

	@Override
	public Producto addProducto(ProductoDto productoDto) {
		Producto producto = new Producto();
		Categoria categoria = categoriaRepo.findByNombre(productoDto.getCategoria());
		if ( categoria == null )throw new CategoriaException("la categoria no existe");
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
		if (productoEditado==null)throw new ProductoException("no existe el id del producto");
		FuncionUtil util = new FuncionUtil();
		BeanUtils.copyProperties(producto, productoEditado, util.getNullPropertyNames(producto));
		
		return productoRepo.save(productoEditado);
	}

	@Override
	public Producto obtenerProductoPorName(String nombre) {
		return productoRepo.findProductoByNombre(nombre);
	}

	@Override
	public Page<Producto> obtenerProductosPorCosto(Integer costo) {
		// TODO Auto-generated method stub
		Pageable pag = PageRequest.of(1, 1);
		return productoRepo.findProductoByCosto(costo,pag);
	}



//	@Override
//	public Page<ReporteProducto> listarPaginado(int size, int numeroPagina) {
//		Pageable pageableRequest = new PageRequest(numeroPagina, size, Sort.Direction.DESC, id);
//
//		return productoRepo.buscarPorPagina( pageableRequest);
//	}

}
