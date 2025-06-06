package com.mercado.stock.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column
    private String descripcion;

    @Column(unique = true)
    private String codigoBarra;

    @Column(nullable = false)
    private Double precioCompra;

    @Column(nullable = false)
    private Double precioVenta;

    @Column(nullable = false)
    private Integer stock = 0;

    @Column
    private Integer stockMinimo = 0;

    @Column(nullable = false)
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Transient
    public Double getGanancia() {
        if (precioCompra != null && precioVenta != null && precioCompra != 0) {
            return ((precioVenta - precioCompra) / precioCompra) * 100;
        }
        return 0.0;
    }
}
