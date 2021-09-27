package com.ao.juego.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ao.juego.model.Producto;
import com.ao.juego.model.custom.CategoriaCantidad;
import com.ao.juego.model.custom.ProductoConNombre;
import com.ao.juego.model.custom.ProductoPorIdYNombre;

public interface ProductoRepo extends JpaRepository<Producto, Integer> {
	Producto findProductoById(Integer id);

	Producto findProductoByNombre(String nombre);

	Page<Producto> findProductoByCosto(Integer costo, Pageable pag);

	@Query("select new com.ao.juego.model.custom.ProductoPorIdYNombre(c.id,c.nombre) " + "from  Producto c")
	List<ProductoPorIdYNombre> obtenerReporteProductos();

	@Query("select new com.ao.juego.model.custom.ProductoConNombre(pro.nombre," + "pro.costo,cat.nombre)"
			+ "from Producto pro inner join Categoria cat on " + "pro.idCategoria = cat.id")
	List<ProductoConNombre> obtenerReporteProduct();

	@Query("select new com.ao.juego.model.custom.CategoriaCantidad(pro.idCategoria,sum(c.cantidad)) "
			+ "from  ProductoVenta c inner join Producto pro on " + "pro.id = c.idProducto "
			+ "group by pro.idCategoria order by sum(c.cantidad) DESC")
	List<CategoriaCantidad> obtenerCategoriasCantidadConPaginacion(Pageable pag);

	@Query("select new com.ao.juego.model.custom.ProductoPorIdYNombre(c.id,c.nombre) " + "from  Producto c order by c.id")
	List<ProductoPorIdYNombre> obternerReporteProductoConPaginacion(Pageable pageable);
}
