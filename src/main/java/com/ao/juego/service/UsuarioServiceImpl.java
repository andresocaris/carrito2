package com.ao.juego.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.ao.juego.controller.dto.ProductoCantidadDetailDto;
import com.ao.juego.controller.dto.ProductosCantidadDto;
import com.ao.juego.exceptions.UsuarioException;
import com.ao.juego.model.Producto;
import com.ao.juego.model.ProductoVenta;
import com.ao.juego.model.Usuario;
import com.ao.juego.model.Venta;
import com.ao.juego.repository.UsuarioRepo;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepo usuarioRepo;
	private final ProductoService productoService;
	private final VentaService ventaService;
	private final ProductoVentaService productoVentaService;

	public UsuarioServiceImpl(UsuarioRepo usuarioRepo, ProductoService productoService, VentaService ventaService, ProductoVentaService productoVentaService) {
		this.usuarioRepo = usuarioRepo;
		this.productoService = productoService;
		this.ventaService = ventaService;
		this.productoVentaService = productoVentaService;
	}

	@Override
	public Usuario obtenerUsuarioPorNombreContrasena(String nombre, String pwd) {
		return usuarioRepo.getByNombreAndPwd(nombre, pwd);
	}

	@Override
	public Usuario busquedaPorNombreContrasena(String username, String pwd) {
		return usuarioRepo.getByNombreAndPwd(username, pwd);
	}

	@Override
	public Usuario agregarUsuario(Usuario usuario) {
		Usuario usuarioBuscado = usuarioRepo.getByNombre(usuario.getNombre());
		if ( usuarioBuscado != null ) {
			throw new UsuarioException("ya existe el usuario con el nombre");
		}else return usuarioRepo.save(usuario);
	}

	@Override
	public Venta generarVenta(ProductosCantidadDto productos, Integer idUsuario) {
		Integer montoVenta = 0;
		for (ProductoCantidadDetailDto productoCantidad: productos.getProductos()) {
			String nombre = productoCantidad.getNombre();
			Integer cantidad = productoCantidad.getCantidad();
			Producto producto = productoService.obtenerProductoPorName(nombre);
			montoVenta += producto.getCosto()*cantidad;			
		}
		Date date = new Date();
		Venta venta = new Venta();
		venta.setIdUsuario(idUsuario);
		venta.setMonto(montoVenta);
		venta.setFecha(date);
		Venta ventaCreada = ventaService.crearVenta(venta);
		Integer idVenta = ventaCreada.getId();
		
		for (ProductoCantidadDetailDto productoCantidad: productos.getProductos()) {
			String nombre = productoCantidad.getNombre();
			Integer cantidad = productoCantidad.getCantidad();
			Integer idProducto = productoService.obtenerProductoPorName(nombre).getId();
			ProductoVenta productoVenta = new ProductoVenta();
			productoVenta.setCantidad(cantidad);
			productoVenta.setIdProducto(idProducto);
			productoVenta.setIdVenta(idVenta);
			ProductoVenta productoVentaCreado = productoVentaService.crearProductoVenta(productoVenta);
		}
		
		return ventaCreada;		
	}

}
