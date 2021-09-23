package com.ao.juego.service;

import com.ao.juego.controller.dto.ProductosCantidadDto;
import com.ao.juego.model.Usuario;
import com.ao.juego.model.Venta;

public interface UsuarioService {
	Usuario obtenerUsuarioPorNombreContrasena(String nombre,String pwd);

	Usuario busquedaPorNombreContrasena(String username, String pwd);

	Usuario agregarUsuario(Usuario usuario);

	Venta generarVenta(ProductosCantidadDto productos, Integer idUsuario);
}
