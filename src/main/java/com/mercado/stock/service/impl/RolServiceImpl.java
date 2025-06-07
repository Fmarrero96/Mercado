package com.mercado.stock.service.impl;

import com.mercado.stock.entity.Rol;
import com.mercado.stock.repository.RolRepository;
import com.mercado.stock.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public Rol findById(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        rolRepository.deleteById(id);
    }
} 