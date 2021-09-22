package com.ao.juego.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PostMapping("/add")
	public ResponseEntity<Object> addProducto(@RequestBody ProductoDto productoDto) {
		Producto producto = productoService.addProducto(productoDto);

		if (producto == null) {
			return new ResponseEntity<>("no existe el la categoria", HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(producto, HttpStatus.OK);
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Object> editarProducto(@RequestBody Producto producto) {
		Producto productoEditado = productoService.editarProducto(producto);
		if (productoEditado == null) {
			return new ResponseEntity<>("No existe ese producto con ese Id", HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(productoEditado, HttpStatus.OK);
	}
	
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
	
	@GetMapping("/reporte")
	public ResponseEntity<List<ReporteProducto>> reporteProductos() {
		List<ReporteProducto> productos = productoService.reporteProductos();
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}
	
	@GetMapping("/listado-paginacion/{tamanoPagina}/{pagina}")
	public ResponseEntity<List<Producto>> obtenerProductos(@PathVariable("tamanoPagina") int tamanoPagina
			,@PathVariable("pagina") int pagina) {
		
		Page<Producto> productosPage = productoService.obtenerProductosPaginas(tamanoPagina,pagina-1);
		List<Producto> productosList = productosPage.getContent();
		
		return new ResponseEntity<>(productosList, HttpStatus.OK);
	}
	@GetMapping("/listado")
	public ResponseEntity<List<Producto>> obtenerProductos() {
		List<Producto> productos = productoService.obtenerProductos();
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public ResponseEntity<List<Producto>> testProductos() {
		List<Producto> productos = productoService.obtenerProductosPorCosto(5301).getContent();
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}
}
