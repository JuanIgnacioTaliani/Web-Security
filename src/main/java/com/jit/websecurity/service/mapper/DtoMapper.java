package com.jit.websecurity.service.mapper;

import com.jit.websecurity.model.dto.UsuarioDto;
import com.jit.websecurity.model.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DtoMapper implements Function<Usuario, UsuarioDto> {

    @Override
    public UsuarioDto apply(Usuario usuario) {
        return UsuarioDto.builder()
                .idUsuario(usuario.getIdUsuario())
                .email(usuario.getEmail())
                .nombreUsuario(usuario.getNombreUsuario())
                .contrasena(usuario.getContrasena())
                .roles(usuario
                        .getRoles()
                        .stream()
                        .map(rol -> rol.getNombre().name())
                        .collect(Collectors.toSet()))
                .build();
    }
}
