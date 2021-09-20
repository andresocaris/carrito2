package com.ao.juego.service;

import org.springframework.stereotype.Service;

import com.ao.juego.model.Usuario;
import com.ao.juego.repository.UsuarioRepo;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepo usuarioRepo;

	public UsuarioServiceImpl(UsuarioRepo usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}

	@Override
	public Usuario obtenerUsuarioPorNombreContrasena(String nombre, String pwd) {
		// TODO Auto-generated method stub
		return usuarioRepo.getByNombreAndPwd(nombre, pwd);
	}

	@Override
	public Usuario busquedaPorNombreContrasena(String username, String pwd) {
		return usuarioRepo.getByNombreAndPwd(username, pwd);
	}

	@Override
	public Usuario agregarUsuario(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

}
