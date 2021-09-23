package com.ao.juego.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.ao.juego.controller.dto.VentaBusquedaDetail;
import com.ao.juego.model.Venta;
import com.ao.juego.repository.VentaRepo;
import com.ao.juego.util.Tiempo;

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
	@Override
	public List<Venta> buscarEntreFechas(VentaBusquedaDetail ventaBusquedaDetail) {
		String inicio = ventaBusquedaDetail.getFechaInicio();
		String fin = ventaBusquedaDetail.getFechaFin();
		Tiempo tiempo = new Tiempo();
		return ventaRepo.findByFechaBetween(tiempo.convertStrinToDate(inicio),
				tiempo.convertStrinToDate(fin));
	}

}
