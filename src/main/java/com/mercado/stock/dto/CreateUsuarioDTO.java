package com.mercado.stock.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUsuarioDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @NotNull(message = "El ID del rol es obligatorio")
    private Long rolId;
} 