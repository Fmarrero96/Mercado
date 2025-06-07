package com.mercado.stock.controller;

import com.mercado.stock.dto.CreateUsuarioDTO;
import com.mercado.stock.dto.UsuarioResponseDTO;
import com.mercado.stock.entity.Usuario;
import com.mercado.stock.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponseDTO> listar() {
        return usuarioService.findAll().stream()
                .map(UsuarioResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok(UsuarioResponseDTO.fromEntity(usuario));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@Valid @RequestBody CreateUsuarioDTO createUsuarioDTO) {
        Usuario nuevoUsuario = usuarioService.crearUsuario(createUsuarioDTO);
        return ResponseEntity.ok(UsuarioResponseDTO.fromEntity(nuevoUsuario));
    }
}
