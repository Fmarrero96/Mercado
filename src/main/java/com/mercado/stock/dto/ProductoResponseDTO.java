package com.mercado.stock.dto;

import com.mercado.stock.entity.Producto;
import lombok.Data;

@Data
public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String codigoBarra;
    private Double precioCompra;
    private Double precioVenta;
    private Integer stock;
    private Integer stockMinimo;
    private Boolean activo;
    private Long categoriaId;

    public static ProductoResponseDTO fromEntity(Producto producto) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setCodigoBarra(producto.getCodigoBarra());
        dto.setPrecioCompra(producto.getPrecioCompra());
        dto.setPrecioVenta(producto.getPrecioVenta());
        dto.setStock(producto.getStock());
        dto.setStockMinimo(producto.getStockMinimo());
        dto.setActivo(producto.getActivo());
        if (producto.getCategoria() != null) {
            dto.setCategoriaId(producto.getCategoria().getId());
        }
        return dto;
    }
} 