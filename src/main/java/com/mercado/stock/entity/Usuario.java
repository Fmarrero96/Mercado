package com.mercado.stock.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Rol rol;
}
