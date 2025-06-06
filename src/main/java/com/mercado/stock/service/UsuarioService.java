package com.mercado.stock.service;

import com.mercado.stock.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario save(Usuario usuario);
}
