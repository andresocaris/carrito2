package com.ao.juego.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ao.juego.controller.dto.ProductoDetailDto;
import com.ao.juego.controller.dto.ProductoDto;
import com.ao.juego.controller.dto.ProductoReportDto;
import com.ao.juego.model.Producto;
import com.ao.juego.model.custom.ReporteProduct;
import com.ao.juego.model.custom.ReporteProducto;
import com.ao.juego.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	ProductoService productoService;

	

	@PreAuthorize("permitAll()")
	@GetMapping("/listado")
	public ResponseEntity<List<Producto>> obtenerProductos() {
		List<Producto> productos = productoService.obtenerProductos();
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}

	@PreAuthorize("permitAll()")
	@GetMapping("/reporte")
	public ResponseEntity<List<ReporteProducto>> reporteProductos() {
		List<ReporteProducto> productos = productoService.reporteProductos();
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}
	@PreAuthorize("permitAll()")
	@PostMapping("/add")
	public ResponseEntity<Producto> addProducto(@RequestBody ProductoDto productoDto) {
		Producto producto = productoService.addProducto(productoDto);
		return new ResponseEntity<>(producto, HttpStatus.OK);
	}
	
	@PreAuthorize("permitAll()")
	@GetMapping("/listar")
	public ResponseEntity<ProductoReportDto> listarProductos() {
		ProductoReportDto productoReportDto = new ProductoReportDto();
		List<ReporteProduct> resultadoReporte = productoService.obtenerProductosPorCampos();

		for (ReporteProduct reporteProduct : resultadoReporte) {
			ProductoDetailDto productoDetailDto = new ProductoDetailDto();
			productoDetailDto.setCosto(reporteProduct.getCosto());
			productoDetailDto.setNombre(reporteProduct.getNombre());
			productoDetailDto.setCategoria(reporteProduct.getNombreCategoria());
			productoReportDto.add(productoDetailDto);
		}
		return new ResponseEntity<>(productoReportDto, HttpStatus.OK);
	}
	@PreAuthorize("permitAll()")
	@PutMapping("/editar")
	public ResponseEntity<Producto> addProducto(@RequestBody Producto producto) {
		Producto productoEditado = productoService.editarProducto(producto);
		return new ResponseEntity<>(productoEditado, HttpStatus.OK);
	}

}
