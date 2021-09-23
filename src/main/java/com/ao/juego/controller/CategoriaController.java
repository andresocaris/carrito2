package com.ao.juego.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ao.juego.controller.dto.CategoriaDto;
import com.ao.juego.controller.dto.CategoriaReporteDetailDto;
import com.ao.juego.controller.dto.CategoriaReporteDto;
import com.ao.juego.model.Categoria;
import com.ao.juego.model.custom.CategoriaCantidad;
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
	
	@GetMapping("/obtener-categoria-mas-demandada/{tamanoPagina}/{numeroPagina}")
	public ResponseEntity<Object> testeo2(@PathVariable("tamanoPagina") int tamanoPagina 
			, @PathVariable("numeroPagina") int numeroPagina){
		
		List<CategoriaCantidad> categorias = categoriaService.mostrarCategoriasDemandadas(tamanoPagina,numeroPagina-1);
		
		CategoriaReporteDto categoriasDto = new CategoriaReporteDto();
		
		for (CategoriaCantidad categoriaCantidad: categorias) {
			
			Integer idCategoria = categoriaCantidad.getIdCategoria();
			CategoriaReporteDetailDto categoriaDetail = new CategoriaReporteDetailDto();
			String nombreCategoria=categoriaService.obtenerNombreCategoriaPorId(idCategoria);
			categoriaDetail.setNombre(nombreCategoria);
			categoriaDetail.setCantidad((int) categoriaCantidad.getCantidad());
			categoriasDto.add(categoriaDetail);
			
		}		
		return new ResponseEntity<>(categoriasDto,HttpStatus.OK);
	}
}
