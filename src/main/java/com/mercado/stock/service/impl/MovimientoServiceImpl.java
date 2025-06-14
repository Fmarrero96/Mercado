package com.mercado.stock.service.impl;

import com.mercado.stock.entity.Movimiento;
import com.mercado.stock.entity.Producto;
import com.mercado.stock.repository.MovimientoRepository;
import com.mercado.stock.repository.ProductoRepository;
import com.mercado.stock.service.MovimientoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, ProductoRepository productoRepository) {
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Movimiento> findAll() {
        return movimientoRepository.findAll();
    }

    @Override
    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Producto producto = productoRepository.findById(movimiento.getProducto().getId())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        if (movimiento.getTipo() == Movimiento.TipoMovimiento.ENTRADA) {
            producto.setStock(producto.getStock() + movimiento.getCantidad().intValue());
        } else {
            if (producto.getStock() < movimiento.getCantidad().intValue()) {
                throw new IllegalArgumentException("Stock insuficiente");
            }
            producto.setStock(producto.getStock() - movimiento.getCantidad().intValue());
        }

        productoRepository.save(producto);
        return movimientoRepository.save(movimiento);
    }
}
