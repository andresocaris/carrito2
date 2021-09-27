package com.ao.juego.service;

import org.springframework.stereotype.Service;


import com.ao.juego.controller.dto.ProductosCantidadDto;
import com.ao.juego.exceptions.UsuarioException;
import com.ao.juego.model.Usuario;
import com.ao.juego.model.Venta;
import com.ao.juego.repository.UsuarioRepo;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	private final UsuarioRepo usuarioRepo;
	private final VentaService ventaService;
	private final ProductoVentaService productoVentaService;

	public UsuarioServiceImpl(UsuarioRepo usuarioRepo, VentaService ventaService,
			ProductoVentaService productoVentaService) {
		this.usuarioRepo = usuarioRepo;
		this.ventaService = ventaService;
		this.productoVentaService = productoVentaService;
	}

	@Override
	public Usuario obtenerUsuarioPorNombreYContrasena(String username, String pwd) {
		return usuarioRepo.getByNombreAndPwd(username, pwd);
	}

	@Override
	public Usuario agregarUsuario(Usuario usuario) {
		Usuario usuarioBuscado = usuarioRepo.getByNombre(usuario.getNombre());
		if (usuarioBuscado != null) {
			throw new UsuarioException("ya existe el usuario con el nombre");
		} else
			return usuarioRepo.save(usuario);
	}

	@Override
	public Venta generarVenta(ProductosCantidadDto productos, Integer idUsuario) {
		Integer montoVenta = ventaService.ObtenerMontoVenta(productos);
		Venta ventaCreada = ventaService.CrearVentaPorParametros(idUsuario, montoVenta);
		Integer idVenta = ventaCreada.getId();
		productoVentaService.crearRegistrosProductoVenta(idVenta, productos);
		return ventaCreada;
	}
}
