package com.ao.juego.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ao.juego.model.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario,Integer>{

	Usuario getByNombreAndPwd(String username, String pwd);
	Usuario getByNombre(String username);
	
}
