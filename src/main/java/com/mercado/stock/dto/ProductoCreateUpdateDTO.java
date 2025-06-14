package com.mercado.stock.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductoCreateUpdateDTO {

    private Long id;

    @NotBlank(message = "El código de barra es obligatorio")
    private String codigoBarra;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio de compra es obligatorio")
    @Min(value = 0, message = "El precio de compra debe ser mayor o igual a 0")
    private Double precioCompra;

    @NotNull(message = "El precio de venta es obligatorio")
    @Min(value = 0, message = "El precio de venta debe ser mayor o igual a 0")
    private Double precioVenta;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    private Integer stock;

    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(value = 0, message = "El stock mínimo debe ser mayor o igual a 0")
    private Integer stockMinimo;

    @NotNull(message = "El ID de la categoría es obligatorio")
    private Long categoriaId;
} 