package com.mercado.stock.dto;

import com.mercado.stock.entity.Usuario;
import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String nombre;
    private String email;
    private Long rolId;

    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        if (usuario.getRol() != null) {
            dto.setRolId(usuario.getRol().getId());
        }
        return dto;
    }
} 