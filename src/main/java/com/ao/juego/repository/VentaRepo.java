package com.ao.juego.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ao.juego.model.Venta;

public interface VentaRepo extends JpaRepository<Venta, Integer> {
	List<Venta> findByFechaBetween(Date inicio, Date fin);
}
