package com.mercado.stock.service.impl;

import com.mercado.stock.dto.RegistroVentaDTO;
import com.mercado.stock.entity.*;
import com.mercado.stock.repository.*;
import com.mercado.stock.service.VentaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public VentaServiceImpl(VentaRepository ventaRepository, ProductoRepository productoRepository, UsuarioRepository usuarioRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Venta registrarVenta(RegistroVentaDTO dto) {
        Venta venta = new Venta();
        venta.setUsuarioId(dto.getUsuarioId());
        venta.setDetalles(new ArrayList<>());

        double total = 0;

        for (RegistroVentaDTO.DetalleDTO d : dto.getDetalles()) {
            Producto producto = productoRepository.findById(d.getProductoId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

            if (producto.getStock() < d.getCantidad().intValue()) {
                throw new IllegalArgumentException("Stock insuficiente para " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - d.getCantidad().intValue());
            productoRepository.save(producto);

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVentaId(venta.getId());
            detalle.setProducto(producto);
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecioVenta());
            detalle.setSubtotal(producto.getPrecioVenta() * d.getCantidad());

            total += detalle.getSubtotal();
            venta.getDetalles().add(detalle);
        }

        venta.setTotal(total);
        return ventaRepository.save(venta);
    }

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }
}
