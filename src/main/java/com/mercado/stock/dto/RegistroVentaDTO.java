package com.mercado.stock.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegistroVentaDTO {
    private Long usuarioId;
    private List<DetalleDTO> detalles;

    @Getter
    @Setter
    public static class DetalleDTO {
        private Long productoId;
        private Integer cantidad;
    }
}
