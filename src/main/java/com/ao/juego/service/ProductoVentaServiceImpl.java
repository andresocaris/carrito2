package com.ao.juego.service;

import org.springframework.stereotype.Service;

import com.ao.juego.model.ProductoVenta;
import com.ao.juego.repository.ProductoVentaRepo;

@Service
public class ProductoVentaServiceImpl implements ProductoVentaService{

	private final ProductoVentaRepo productoVentaRepo;
	
	public ProductoVentaServiceImpl(ProductoVentaRepo productoVentaRepo) {
		this.productoVentaRepo = productoVentaRepo;
	}

	@Override
	public ProductoVenta crearProductoVenta(ProductoVenta productoVenta) {
		return productoVentaRepo.save(productoVenta);
	}

}
