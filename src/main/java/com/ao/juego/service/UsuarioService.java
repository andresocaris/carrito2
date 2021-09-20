package com.ao.juego.service;

import com.ao.juego.model.Usuario;

public interface UsuarioService {
	Usuario obtenerUsuarioPorNombreContrasena(String nombre,String pwd);

	Usuario busquedaPorNombreContrasena(String username, String pwd);

	Usuario agregarUsuario(Usuario usuario);
}
