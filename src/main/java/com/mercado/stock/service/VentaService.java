package com.mercado.stock.service;

import com.mercado.stock.dto.RegistroVentaDTO;
import com.mercado.stock.entity.Venta;

import java.util.List;

public interface VentaService {
    Venta registrarVenta(RegistroVentaDTO dto);
    List<Venta> listarVentas();
}
