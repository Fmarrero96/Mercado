package com.mercado.stock.repository;

import com.mercado.stock.entity.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p WHERE p.stock <= :limite")
    List<Producto> findByStockBajo(int limite);
}
