package com.ao.juego.service;

import org.springframework.stereotype.Service;

import com.ao.juego.model.Venta;
import com.ao.juego.repository.VentaRepo;

@Service
public class VentaServiceImpl implements VentaService {
	private final VentaRepo ventaRepo;
	
	public VentaServiceImpl(VentaRepo ventaRepo) {
		this.ventaRepo = ventaRepo;
	}
	@Override
	public Venta crearVenta(Venta venta) {
		return ventaRepo.save(venta);
	}

}
