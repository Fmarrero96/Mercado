package com.mercado.stock.service;

import com.mercado.stock.dto.CreateUsuarioDTO;
import com.mercado.stock.entity.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario save(Usuario usuario);
    Usuario findById(Long id);
    Usuario crearUsuario(CreateUsuarioDTO createUsuarioDTO);
}
