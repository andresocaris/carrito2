package com.ao.juego.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ao.juego.controller.dto.ProductoCantidadDetailDto;
import com.ao.juego.controller.dto.ProductosCantidadDto;
import com.ao.juego.controller.dto.VentaBusquedaDetail;
import com.ao.juego.model.Producto;
import com.ao.juego.model.Venta;
import com.ao.juego.repository.VentaRepo;
import com.ao.juego.util.Tiempo;

@Service
public class VentaServiceImpl implements VentaService {
	private final VentaRepo ventaRepo;
	private final ProductoService productoService;

	public VentaServiceImpl(VentaRepo ventaRepo, ProductoService productoService) {
		this.ventaRepo = ventaRepo;
		this.productoService = productoService;
	}

	@Override
	public Venta CrearVentaPorParametros(Integer idUsuario, Integer montoVenta) {
		Date date = new Date();
		Venta venta = new Venta();
		venta.setIdUsuario(idUsuario);
		venta.setMonto(montoVenta);
		venta.setFecha(date);
		Venta ventaCreada = crearVenta(venta);
		return ventaCreada;
	}

	@Override
	public Venta crearVenta(Venta venta) {
		return ventaRepo.save(venta);
	}

	@Override
	public List<Venta> buscarVentasPorVentaBusquedaDetail(VentaBusquedaDetail ventaBusquedaDetail) {
		String inicio = ventaBusquedaDetail.getFechaInicio();
		String fin = ventaBusquedaDetail.getFechaFin();
		Tiempo tiempo = new Tiempo();
		return ventaRepo.findByFechaBetween(tiempo.convertStrinToDate(inicio), tiempo.convertStrinToDate(fin));
	}

	@Override
	public Integer ObtenerMontoVenta(ProductosCantidadDto productos) {
		Integer montoVenta=0;
		for (ProductoCantidadDetailDto productoCantidad : productos.getProductos()) {
			String nombre = productoCantidad.getNombre();
			Integer cantidad = productoCantidad.getCantidad();
			Producto producto = productoService.obtenerProductoPorNombre(nombre);
			montoVenta += producto.getCosto() * cantidad;
		}
		return montoVenta;
	}

}
