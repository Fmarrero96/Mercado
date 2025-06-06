package com.mercado.stock.controller;

import com.mercado.stock.dto.RegistroVentaDTO;
import com.mercado.stock.entity.Venta;
import com.mercado.stock.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "http://localhost:4200")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody RegistroVentaDTO dto) {
        try {
            return ResponseEntity.ok(ventaService.registrarVenta(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Venta> listar() {
        return ventaService.listarVentas();
    }
}
