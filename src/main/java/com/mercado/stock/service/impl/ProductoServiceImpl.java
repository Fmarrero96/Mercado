package com.mercado.stock.service.impl;

import com.mercado.stock.dto.ProductoCreateUpdateDTO;
import com.mercado.stock.entity.Categoria;
import com.mercado.stock.entity.Producto;
import com.mercado.stock.repository.CategoriaRepository;
import com.mercado.stock.repository.ProductoRepository;
import com.mercado.stock.service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    @Transactional
    public Producto save(ProductoCreateUpdateDTO productoDTO) {
        Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con ID: " + productoDTO.getCategoriaId()));

        Producto producto;
        if (productoDTO.getId() != null) { // Si es una actualización
            producto = productoRepository.findById(productoDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + productoDTO.getId()));
        } else { // Si es una creación
            producto = new Producto();
        }

        producto.setCodigoBarra(productoDTO.getCodigoBarra());
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecioCompra(productoDTO.getPrecioCompra());
        producto.setPrecioVenta(productoDTO.getPrecioVenta());
        producto.setStock(productoDTO.getStock());
        producto.setStockMinimo(productoDTO.getStockMinimo());
        // Aquí puedes manejar el campo activo si es necesario, si no está en el DTO, mantendrá el valor por defecto o el existente.
        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> obtenerProductosConStockBajo(int limite) {
        return productoRepository.findByStockBajo(limite);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findByCodigoBarra(String codigoBarra) {
        return productoRepository.findByCodigoBarra(codigoBarra);
    }
}
