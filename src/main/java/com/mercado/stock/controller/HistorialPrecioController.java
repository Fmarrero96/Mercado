package com.mercado.stock.controller;

import com.mercado.stock.entity.HistorialPrecio;
import com.mercado.stock.service.HistorialPrecioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial-precios")
@CrossOrigin(origins = "http://localhost:4200")
public class HistorialPrecioController {

    private final HistorialPrecioService historialPrecioService;

    public HistorialPrecioController(HistorialPrecioService historialPrecioService) {
        this.historialPrecioService = historialPrecioService;
    }

    @PostMapping
    public HistorialPrecio registrar(@RequestBody HistorialPrecio historial) {
        return historialPrecioService.registrar(historial);
    }

    @GetMapping("/producto/{id}")
    public List<HistorialPrecio> obtenerPorProducto(@PathVariable Long id) {
        return historialPrecioService.obtenerPorProducto(id);
    }
}
