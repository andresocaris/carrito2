package com.ao.juego.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ao.juego.model.ProductoVenta;
import com.ao.juego.model.custom.ProductoCantidad;

public interface ProductoVentaRepo extends JpaRepository<ProductoVenta,Integer>{
	@Query("select new com.ao.juego.model.custom.ProductoCantidad(c.idProducto,sum(c.cantidad)) "
			+ "from  ProductoVenta c group by c.idProducto order by sum(c.cantidad) DESC")
	List<ProductoCantidad> obtenerMejoresProductos(Pageable pageable);
}
