package com.mercado.stock.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "venta_id")
    private Long ventaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private Double cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;
    
    private Double subtotal;
}
