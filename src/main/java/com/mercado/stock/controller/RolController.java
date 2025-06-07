package com.mercado.stock.controller;

import com.mercado.stock.entity.Rol;
import com.mercado.stock.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @GetMapping
    public List<Rol> listarRoles() {
        return rolService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRol(@PathVariable Long id) {
        return ResponseEntity.ok(rolService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol) {
        return ResponseEntity.ok(rolService.save(rol));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Long id, @RequestBody Rol rol) {
        rol.setId(id);
        return ResponseEntity.ok(rolService.save(rol));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        rolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 