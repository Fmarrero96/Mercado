package com.mercado.stock.service;

import com.mercado.stock.entity.HistorialPrecio;

import java.util.List;

public interface HistorialPrecioService {
    HistorialPrecio registrar(HistorialPrecio historial);
    List<HistorialPrecio> obtenerPorProducto(Long productoId);
}
