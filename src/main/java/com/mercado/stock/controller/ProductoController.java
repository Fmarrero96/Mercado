package com.mercado.stock.controller;

import com.mercado.stock.dto.ProductoCreateUpdateDTO;
import com.mercado.stock.dto.ProductoResponseDTO;
import com.mercado.stock.entity.Producto;
import com.mercado.stock.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public List<ProductoResponseDTO> listar() {
        return productoService.findAll().stream()
                .map(ProductoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> getProducto(@PathVariable Long id) {
        Producto producto = productoService.findById(id).orElse(null);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ProductoResponseDTO.fromEntity(producto));
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crear(@Valid @RequestBody ProductoCreateUpdateDTO productoDTO) {
        Producto nuevoProducto = productoService.save(productoDTO);
        return ResponseEntity.ok(ProductoResponseDTO.fromEntity(nuevoProducto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoCreateUpdateDTO productoDTO) {
        productoDTO.setId(id);
        Producto productoActualizado = productoService.save(productoDTO);
        return ResponseEntity.ok(ProductoResponseDTO.fromEntity(productoActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stock-bajo")
    public List<Producto> productosConStockBajo(@RequestParam(defaultValue = "5") int limite) {
        return productoService.obtenerProductosConStockBajo(limite);
    }

    @GetMapping("/codigo-barra/{codigoBarra}")
    public ResponseEntity<Producto> getByCodigoBarra(@PathVariable String codigoBarra) {
        return productoService.findByCodigoBarra(codigoBarra)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
