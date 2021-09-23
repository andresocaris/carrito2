package com.ao.juego.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ao.juego.controller.dto.CategoriaDto;
import com.ao.juego.model.Categoria;
import com.ao.juego.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	private final CategoriaService categoriaService;
	
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	@PostMapping("/crear")
	public ResponseEntity<Object> crearCategoria(@RequestBody CategoriaDto categoriaDto){
		Categoria categoriaNuevo = categoriaService.crearCategoria(categoriaDto.getNombre());
		return new ResponseEntity<>(categoriaNuevo,HttpStatus.OK);
	}
	@GetMapping("/listar")
	public ResponseEntity<Object> listarCategoria(){
		List<Categoria> categorias = categoriaService.listarCategorias();
		return new ResponseEntity<>(categorias,HttpStatus.OK);
	}
}
