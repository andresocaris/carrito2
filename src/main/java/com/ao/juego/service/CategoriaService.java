package com.ao.juego.service;

import java.util.List;

import com.ao.juego.model.Categoria;
import com.ao.juego.model.custom.CategoriaCantidad;

public interface CategoriaService {
	public Categoria crearCategoriaPorNombre(String nombre);

	public List<Categoria> obtenerCategorias();

	public List<CategoriaCantidad> obtenerCategoriasMasDemandadasConPaginacion(int tamanoPagina, int numeroPagina);

	public String obtenerNombreCategoriaPorId(Integer idCategoria);
}
