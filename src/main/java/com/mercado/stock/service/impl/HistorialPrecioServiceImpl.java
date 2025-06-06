package com.mercado.stock.service.impl;

import com.mercado.stock.entity.HistorialPrecio;
import com.mercado.stock.repository.HistorialPrecioRepository;
import com.mercado.stock.service.HistorialPrecioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialPrecioServiceImpl implements HistorialPrecioService {

    private final HistorialPrecioRepository repository;

    public HistorialPrecioServiceImpl(HistorialPrecioRepository repository) {
        this.repository = repository;
    }

    @Override
    public HistorialPrecio registrar(HistorialPrecio historial) {
        return repository.save(historial);
    }

    @Override
    public List<HistorialPrecio> obtenerPorProducto(Long productoId) {
        return repository.findByProductoIdOrderByFechaDesc(productoId);
    }
}
