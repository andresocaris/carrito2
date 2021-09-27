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
import com.ao.juego.model.custom.ProductoCantidad;
import com.ao.juego.model.custom.ProductoConNombre;
import com.ao.juego.model.custom.ProductoPorIdYNombre;
import com.ao.juego.repository.CategoriaRepo;
import com.ao.juego.repository.ProductoRepo;
import com.ao.juego.repository.ProductoVentaRepo;
import com.ao.juego.util.FuncionUtil;

@Service
public class ProductoServiceImpl implements ProductoService {

	private final ProductoRepo productoRepo;
	private final CategoriaRepo categoriaRepo;
	private final ProductoVentaRepo productoVentaRepo;

	public ProductoServiceImpl(ProductoRepo productoRepo, CategoriaRepo categoriaRepo,
			ProductoVentaRepo productoVentaRepo) {
		this.productoRepo = productoRepo;
		this.categoriaRepo = categoriaRepo;
		this.productoVentaRepo = productoVentaRepo;
	}

	@Override
	public Page<Producto> obtenerPageProductosConPaginacion(int size, int pagina) {
		Pageable pag = PageRequest.of(pagina, size);
		return productoRepo.findAll(pag);
	}

	@Override
	public List<Producto> obtenerProductos() {
		return productoRepo.findAll();
	}

	@Override
	public List<ProductoPorIdYNombre> obtenerReporteProductos() {
		return productoRepo.obtenerReporteProductos();
	}

	@Override
	public Producto addProductoPorDto(ProductoDto productoDto) {
		Producto producto = new Producto();
		Categoria categoria = categoriaRepo.findByNombre(productoDto.getCategoria());
		if (categoria == null)
			throw new CategoriaException("la categoria no existe");
		Integer idCategoria = categoria.getId();
		producto.setNombre(productoDto.getNombre());
		producto.setCosto(productoDto.getCosto());
		producto.setIdCategoria(idCategoria);
		productoRepo.save(producto);
		return producto;
	}

	@Override
	public List<ProductoConNombre> obtenerProductosPorCampos() {
		return productoRepo.obtenerReporteProduct();

	}

	@Override
	public Producto editarProducto(Producto producto) {
		Producto productoEditado = productoRepo.findProductoById(producto.getId());
		if (productoEditado == null)
			throw new ProductoException("no existe el id del producto");
		FuncionUtil util = new FuncionUtil();
		BeanUtils.copyProperties(producto, productoEditado, util.getNullPropertyNames(producto));

		return productoRepo.save(productoEditado);
	}

	@Override
	public Producto obtenerProductoPorNombre(String nombre) {
		return productoRepo.findProductoByNombre(nombre);
	}

	@Override
	public Page<Producto> obtenerPageProductosPorCostoConPaginacion(Integer costo,int numeroPagina, int tamanoPagina) {
		Pageable pag = PageRequest.of(numeroPagina - 1, tamanoPagina);
		return productoRepo.findProductoByCosto(costo, pag);
	}

	@Override
	public List<ProductoCantidad> obtenerProductosMasVendidosConPaginacion(int numeroPagina, int tamanoPagina) {
		Pageable pag = PageRequest.of(numeroPagina - 1, tamanoPagina);
		return productoVentaRepo.obtenerMejoresProductos(pag);
	}

	@Override
	public String obtenerNombreDelProductoPorId(int idProducto) {
		return productoRepo.findProductoById(idProducto).getNombre();
	}

	@Override
	public List<ProductoPorIdYNombre> reporteProductosConPaginacion(int cantidadPagina, int numeroPagina) {
		Pageable pag = PageRequest.of(numeroPagina - 1, cantidadPagina);
		return productoRepo.obternerReporteProductoConPaginacion(pag);
	}
}
