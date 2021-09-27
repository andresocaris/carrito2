package com.ao.juego.service;

import org.springframework.stereotype.Service;

import com.ao.juego.controller.dto.ProductoCantidadDetailDto;
import com.ao.juego.controller.dto.ProductosCantidadDto;
import com.ao.juego.model.ProductoVenta;
import com.ao.juego.repository.ProductoVentaRepo;

@Service
public class ProductoVentaServiceImpl implements ProductoVentaService {
	private final ProductoVentaRepo productoVentaRepo;
	private final ProductoService productoService;

	public ProductoVentaServiceImpl(ProductoVentaRepo productoVentaRepo, ProductoService productoService) {
		this.productoVentaRepo = productoVentaRepo;
		this.productoService = productoService;
	}

	@Override
	public void crearRegistrosProductoVenta(Integer idVenta, ProductosCantidadDto productos) {
		for (ProductoCantidadDetailDto productoCantidad : productos.getProductos()) {
			String nombre = productoCantidad.getNombre();
			Integer cantidad = productoCantidad.getCantidad();
			Integer idProducto = productoService.obtenerProductoPorNombre(nombre).getId();
			ProductoVenta productoVenta = new ProductoVenta();
			productoVenta.setCantidad(cantidad);
			productoVenta.setIdProducto(idProducto);
			productoVenta.setIdVenta(idVenta);
			@SuppressWarnings("unused")
			ProductoVenta productoVentaCreado = crearProductoVenta(productoVenta);
		}	
	}
	
	@Override
	public ProductoVenta crearProductoVenta(ProductoVenta productoVenta) {
		return productoVentaRepo.save(productoVenta);
	}
	
}
