package com.ao.juego.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.ao.juego.controller.dto.ProductoCantidadDetailDto;
import com.ao.juego.controller.dto.ProductosCantidadDto;
import com.ao.juego.controller.dto.UsuarioDto;
import com.ao.juego.controller.dto.UsuarioDto2;
import com.ao.juego.controller.dto.VentaDetail;
import com.ao.juego.controller.dto.VentaDto;
import com.ao.juego.model.Usuario;
import com.ao.juego.model.Venta;
import com.ao.juego.service.ProductoService;
import com.ao.juego.service.UsuarioService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioService usuarioService;
	private final ProductoService productoService;

	public UsuarioController(UsuarioService usuarioService, ProductoService productoService) {
		this.usuarioService = usuarioService;
		this.productoService = productoService;
	}

	@PostMapping("/obtener-usuario")
	public ResponseEntity<UsuarioDto> obtenerUsuario(HttpServletRequest request, @RequestParam("user") String username,
			@RequestParam("password") String pwd) {
		Usuario usuario = usuarioService.busquedaPorNombreContrasena(username, pwd);
		String token = getJWTToken(username);
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setNombre(username);
		usuarioDto.setPwd(pwd);
		usuarioDto.setToken(token);
		HttpSession miSession = request.getSession();
		miSession.setAttribute("usuario", username);
		miSession.setAttribute("idUsuario", usuario.getId());

		Map<String, Integer> mapa = new HashMap<>();
		miSession.setAttribute("productos", mapa);

		return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
	}

	@PostMapping("/agregar")
	public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioDto2 usuarioDto2) {
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setNombre(usuarioDto2.getNombre());
		nuevoUsuario.setPwd(usuarioDto2.getPwd());
		Usuario usuarioCreado = usuarioService.agregarUsuario(nuevoUsuario);
		return new ResponseEntity<>(usuarioCreado, HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/agregar-productos")
	public ResponseEntity<ProductosCantidadDto> agregarProductosCarrito(HttpServletRequest request,
			@RequestBody ProductosCantidadDto productosCantidadDto) {

		HttpSession miSession = request.getSession();

		@SuppressWarnings("unchecked")
		Map<String, Integer> productoCantidad = (Map<String, Integer>) miSession.getAttribute("productos");

		for (ProductoCantidadDetailDto productoCantidadDetailDto : productosCantidadDto.getProductos()) {
			String nombre = productoCantidadDetailDto.getNombre();
			Integer cantidadAgregar = productoCantidadDetailDto.getCantidad();
			if (!productoCantidad.containsKey(nombre))
				productoCantidad.put(nombre, 0);
			Integer cantidadAnterior = productoCantidad.get(nombre);
			productoCantidad.put(nombre, cantidadAnterior + cantidadAgregar);
		}

		ProductosCantidadDto productosCantidadDtoSalida = new ProductosCantidadDto();

		for (Map.Entry<String, Integer> elemento : productoCantidad.entrySet()) {
			String nombre = elemento.getKey();
			Integer cantidad = elemento.getValue();
			Integer costo = productoService.obtenerProductoPorNombre(nombre).getCosto();
			ProductoCantidadDetailDto productoCantidadDetailDto = new ProductoCantidadDetailDto();
			productoCantidadDetailDto.setNombre(nombre);
			productoCantidadDetailDto.setCantidad(cantidad);
			productoCantidadDetailDto.setCosto(costo);
			productosCantidadDtoSalida.add(productoCantidadDetailDto);
		}
		miSession.setAttribute("productos", productoCantidad);

		return new ResponseEntity<>(productosCantidadDtoSalida, HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/generar-venta")
	public ResponseEntity<VentaDto> generarVentas(HttpServletRequest request) {
		HttpSession miSession = request.getSession();
		Integer idUsuario = (Integer) miSession.getAttribute("idUsuario");
		@SuppressWarnings("unchecked")
		Map<String, Integer> productoCantidad = (Map<String, Integer>) miSession.getAttribute("productos");
		ProductosCantidadDto productos = new ProductosCantidadDto();
		for (Map.Entry<String, Integer> e : productoCantidad.entrySet()) {
			ProductoCantidadDetailDto productoCantidadDetailDto = new ProductoCantidadDetailDto();
			String nombre = e.getKey();
			Integer cantidad = e.getValue();
			Integer costo = productoService.obtenerProductoPorNombre(nombre).getCosto();
			productoCantidadDetailDto.setNombre(nombre);
			productoCantidadDetailDto.setCantidad(cantidad);
			productoCantidadDetailDto.setCosto(costo);
			productos.add(productoCantidadDetailDto);
		}
		String nombreUsuario = (String) miSession.getAttribute("usuario");
		Venta venta = usuarioService.generarVenta(productos, idUsuario);
		VentaDetail ventaDetail = new VentaDetail();
		ventaDetail.setDate(venta.getFecha());
		ventaDetail.setMontoVenta(venta.getMonto());
		ventaDetail.setNombreUsuario(nombreUsuario);
		VentaDto ventaDto = new VentaDto();
		ventaDto.setVentaDetail(ventaDetail);
		ventaDto.setProductosCantidadDto(productos);
		return new ResponseEntity<>(ventaDto, HttpStatus.OK);
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
