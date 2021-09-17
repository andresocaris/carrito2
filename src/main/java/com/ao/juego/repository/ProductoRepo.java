package com.ao.juego.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ao.juego.model.Producto;
import com.ao.juego.model.custom.ReporteProduct;
import com.ao.juego.model.custom.ReporteProducto;

public interface ProductoRepo extends JpaRepository<Producto,Integer>{

	
	@Query("select new com.ao.juego.model.custom.ReporteProducto(c.id,c.nombre) "
			+ "from  Producto c")
	List<ReporteProducto> reporteProductos();

	@Query("select new com.ao.juego.model.custom.ReporteProduct(pro.nombre,"
			+ "pro.costo,cat.nombre)"
			+ "from Producto pro inner join Categoria cat on "
			+ "pro.idCategoria = cat.id")
	List<ReporteProduct> mostrarProductos();

	Producto findProductoById(Integer id);

}
