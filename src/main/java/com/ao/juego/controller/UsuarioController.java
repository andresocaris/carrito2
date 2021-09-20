package com.ao.juego.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ao.juego.controller.dto.UsuarioDto;
import com.ao.juego.controller.dto.UsuarioDto2;
import com.ao.juego.model.Usuario;
import com.ao.juego.service.UsuarioService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	@PostMapping("/obtener-usuario")
	public ResponseEntity<UsuarioDto> obtenerUsuario( HttpServletRequest request,@RequestParam("user") String username, @RequestParam("password") String pwd) {
		Usuario usuario = usuarioService.busquedaPorNombreContrasena(username, pwd); 
		System.out.println("usuario"+usuario);
		System.out.println(username);
		System.out.println("----");
		String token=getJWTToken(username);
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setNombre(username);
		usuarioDto.setPwd(pwd);
		usuarioDto.setToken(token);
		HttpSession miSession = request.getSession();
		miSession.setAttribute("usuario", username);
		miSession.setAttribute("idUsuario", usuario.getId());
		miSession.setAttribute("productos", null);
		
		return new ResponseEntity<>(usuarioDto,HttpStatus.OK);
	}
	@PostMapping("/agregar")
	public ResponseEntity<Usuario> crearUsuario( @RequestBody UsuarioDto2 usuarioDto2){
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setNombre(usuarioDto2.getNombre());
		nuevoUsuario.setPwd(usuarioDto2.getPwd());
		Usuario usuarioCreado = usuarioService.agregarUsuario(nuevoUsuario);
		return new ResponseEntity<>(usuarioCreado,HttpStatus.OK);
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/agregar-productos")
	public ResponseEntity<Usuario> agregarProductosCarrito( @RequestBody UsuarioDto2 usuarioDto2){
		Usuario nuevoUsuario = new Usuario();
		return new ResponseEntity<>(nuevoUsuario,HttpStatus.OK);
	}
	
	private String getJWTToken(String username) {
		String secretKey="mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		String token = Jwts.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
