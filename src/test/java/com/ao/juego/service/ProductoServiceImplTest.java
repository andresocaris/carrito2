package com.ao.juego.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ao.juego.exceptions.ProductoException;
import com.ao.juego.model.Producto;
import com.ao.juego.repository.CategoriaRepo;
import com.ao.juego.repository.ProductoRepo;
import com.ao.juego.repository.ProductoVentaRepo;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductoServiceImplTest {

	@Mock
	ProductoRepo productoRepo;
	@Mock
	CategoriaRepo categoriaRepo;
	@Mock
	ProductoVentaRepo productoVentaRepo;
	@InjectMocks
	ProductoServiceImpl productoService;

	@Test
	public void obtenerProductosTest() {
		List<Producto> listaProductos = new ArrayList<>();
		Producto producto1 = new Producto();
		producto1.setId(2);
		producto1.setIdCategoria(2);
		producto1.setNombre("carro");
		producto1.setCosto(100);
		listaProductos.add(producto1);
		when(productoRepo.findAll()).thenReturn(listaProductos);
		assertEquals(productoService.obtenerProductos(), listaProductos);
	}

	@Test
	public void editarProductosTest() {

		Producto productoAntiguo = new Producto();
		productoAntiguo.setId(2);
		productoAntiguo.setCosto(100);
		productoAntiguo.setIdCategoria(1);
		Producto productoEditado = new Producto();
		productoEditado.setId(2);
		productoEditado.setCosto(200);
		productoEditado.setIdCategoria(1);

		when(productoRepo.findProductoById(2)).thenReturn(productoAntiguo);
		when(productoRepo.save(productoEditado)).thenReturn(productoEditado);
		assertEquals(productoService.editarProducto(productoEditado), productoEditado);
	}

	@Test
	public void editarProductoNoExisteTest() {
		Producto productoAntiguo = new Producto();
		productoAntiguo.setId(2);
		productoAntiguo.setCosto(100);
		productoAntiguo.setIdCategoria(1);
		Producto productoEditado = new Producto();
		productoEditado.setId(2);
		productoEditado.setCosto(200);
		productoEditado.setIdCategoria(1);

		when(productoRepo.findProductoById(2)).thenReturn(null);
		assertThrows(ProductoException.class, () -> productoService.editarProducto(productoEditado),
				"no existe el id del producto");
	}

}
