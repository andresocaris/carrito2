package com.ao.juego.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ao.juego.model.Categoria;

public interface CategoriaRepo extends JpaRepository<Categoria,Integer>{

	Categoria findByNombre(String nombre);
	
}
