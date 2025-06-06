package com.mercado.stock.repository;

import com.mercado.stock.entity.HistorialPrecio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialPrecioRepository extends JpaRepository<HistorialPrecio, Long> {
    List<HistorialPrecio> findByProductoIdOrderByFechaDesc(Long productoId);
}
