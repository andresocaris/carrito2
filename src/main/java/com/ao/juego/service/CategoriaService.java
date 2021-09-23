package com.ao.juego.service;

import java.util.List;

import com.ao.juego.model.Categoria;
import com.ao.juego.model.custom.CategoriaCantidad;

public interface CategoriaService {
	public Categoria crearCategoria(String nombre);

	public List<Categoria> listarCategorias();

	public List<CategoriaCantidad> mostrarCategoriasDemandadas(int tamanoPagina, int numeroPagina);

	public String obtenerNombreCategoriaPorId(Integer idCategoria);
}
