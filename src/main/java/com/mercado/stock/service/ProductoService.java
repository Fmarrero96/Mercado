package com.mercado.stock.service;

import com.mercado.stock.dto.ProductoCreateUpdateDTO;
import com.mercado.stock.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Producto save(ProductoCreateUpdateDTO productoDTO);
    void deleteById(Long id);
    List<Producto> obtenerProductosConStockBajo(int limite);
    Optional<Producto> findByCodigoBarra(String codigoBarra);
}
