package com.ao.juego.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ao.juego.model.Categoria;
import com.ao.juego.repository.CategoriaRepo;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private final CategoriaRepo categoriaRepo;

	public CategoriaServiceImpl(CategoriaRepo categoriaRepo) {
		this.categoriaRepo = categoriaRepo;
	}

	@Override
	public Categoria crearCategoria(String nombre) {
		Categoria categoria = categoriaRepo.findByNombre(nombre);
		if (categoria != null)
			return null;
		Categoria categoriaNuevo = new Categoria();
		categoriaNuevo.setNombre(nombre);
		return categoriaRepo.save(categoriaNuevo);
	}

	@Override
	public List<Categoria> listarCategorias() {
		// TODO Auto-generated method stub
		return categoriaRepo.findAll();
	}

}
