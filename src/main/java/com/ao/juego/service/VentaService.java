package com.ao.juego.service;

import java.util.List;

import com.ao.juego.controller.dto.ProductosCantidadDto;
import com.ao.juego.controller.dto.VentaBusquedaDetail;
import com.ao.juego.model.Venta;

public interface VentaService {
	Venta crearVenta(Venta venta);

	List<Venta> buscarVentasPorVentaBusquedaDetail(VentaBusquedaDetail ventaBusquedaDetail);

	Venta CrearVentaPorParametros(Integer idUsuario,Integer montoVenta);
	
	Integer ObtenerMontoVenta(ProductosCantidadDto productos);
}
