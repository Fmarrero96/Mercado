package com.mercado.stock.service;

import com.mercado.stock.entity.Rol;
import java.util.List;

public interface RolService {
    List<Rol> findAll();
    Rol findById(Long id);
    Rol save(Rol rol);
    void deleteById(Long id);
} 