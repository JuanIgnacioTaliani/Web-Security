package com.jit.websecurity.service.mapper;

import com.jit.websecurity.model.dto.UsuarioDto;
import com.jit.websecurity.model.entity.ERol;
import com.jit.websecurity.model.entity.Rol;
import com.jit.websecurity.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EntityMapper implements Function<UsuarioDto, Usuario> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario apply(UsuarioDto usuarioDto) {
        Set<Rol> roles = usuarioDto.getRoles().stream()
                .map(rol -> Rol.builder()
                        .nombre(ERol.valueOf(rol))
                        .build())
                .collect(Collectors.toSet());

        return Usuario.builder()
                .nombreUsuario(usuarioDto.getNombreUsuario())
                .contrasena(passwordEncoder.encode(usuarioDto.getContrasena()))
                .email(usuarioDto.getEmail())
                .roles(roles)
                .build();
    }
}
