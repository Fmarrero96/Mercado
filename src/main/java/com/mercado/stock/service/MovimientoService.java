package com.mercado.stock.service;

import com.mercado.stock.entity.Movimiento;

import java.util.List;

public interface MovimientoService {
    List<Movimiento> findAll();
    Movimiento registrarMovimiento(Movimiento movimiento);
}
