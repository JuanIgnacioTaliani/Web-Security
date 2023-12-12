package com.jit.websecurity.repository;

import com.jit.websecurity.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
