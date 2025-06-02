package com.mercado.stock.service;

import com.mercado.stock.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> findAll();
    Optional<Categoria> findById(Long id);
    Categoria save(Categoria categoria);
    void deleteById(Long id);
}
