package com.ao.juego.service;

import java.util.List;

import com.ao.juego.model.Categoria;

public interface CategoriaService {
	public Categoria crearCategoria(String nombre);

	public List<Categoria> listarCategorias();
}
