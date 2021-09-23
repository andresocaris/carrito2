package com.ao.juego.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ao.juego.controller.dto.VentaBusquedaDetail;
import com.ao.juego.model.Venta;
import com.ao.juego.service.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {
	@Autowired
	VentaService ventaService;
	
	@GetMapping("/buscar-por-fecha")
	public ResponseEntity<List<Venta>> crearUsuario(@RequestBody VentaBusquedaDetail ventaBusquedaDetail) {
		List<Venta> ventas = ventaService.buscarEntreFechas(ventaBusquedaDetail);
		return new ResponseEntity<>(ventas,HttpStatus.OK);
	}
}
