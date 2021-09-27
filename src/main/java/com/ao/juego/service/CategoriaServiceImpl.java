package com.ao.juego.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ao.juego.exceptions.CategoriaException;
import com.ao.juego.model.Categoria;
import com.ao.juego.model.custom.CategoriaCantidad;
import com.ao.juego.repository.CategoriaRepo;
import com.ao.juego.repository.ProductoRepo;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	private final CategoriaRepo categoriaRepo;
	private final ProductoRepo productoRepo;

	public CategoriaServiceImpl(CategoriaRepo categoriaRepo, ProductoRepo productoRepo) {
		this.categoriaRepo = categoriaRepo;
		this.productoRepo = productoRepo;
	}

	@Override
	public Categoria crearCategoriaPorNombre(String nombre) {
		Categoria categoria = categoriaRepo.findByNombre(nombre);
		if ( categoria != null ) throw new CategoriaException("ya existe la categoria con ese nombre");
		Categoria categoriaNuevo = new Categoria();
		categoriaNuevo.setNombre(nombre);
		return categoriaRepo.save(categoriaNuevo);
	}

	@Override
	public List<Categoria> obtenerCategorias() {
		return categoriaRepo.findAll();
	}

	@Override
	public List<CategoriaCantidad> obtenerCategoriasMasDemandadasConPaginacion(int tamanoPagina, int numeroPagina) {
		Pageable pag = PageRequest.of(numeroPagina,tamanoPagina);
		return productoRepo.obtenerCategoriasCantidadConPaginacion(pag);
	}

	@Override
	public String obtenerNombreCategoriaPorId(Integer idCategoria) {
		Categoria categoria =  categoriaRepo.findCategoriaById(idCategoria);
		return categoria.getNombre();
	}
}
