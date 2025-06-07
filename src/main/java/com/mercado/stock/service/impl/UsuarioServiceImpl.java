package com.mercado.stock.service.impl;

import com.mercado.stock.dto.CreateUsuarioDTO;
import com.mercado.stock.entity.Rol;
import com.mercado.stock.entity.Usuario;
import com.mercado.stock.repository.RolRepository;
import com.mercado.stock.repository.UsuarioRepository;
import com.mercado.stock.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario crearUsuario(CreateUsuarioDTO createUsuarioDTO) {
        Rol rol = rolRepository.findById(createUsuarioDTO.getRolId())
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado con id: " + createUsuarioDTO.getRolId()));

        Usuario usuario = new Usuario();
        usuario.setNombre(createUsuarioDTO.getNombre());
        usuario.setEmail(createUsuarioDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(createUsuarioDTO.getPassword()));
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }
}
