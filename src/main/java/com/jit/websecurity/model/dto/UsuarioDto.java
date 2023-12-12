package com.jit.websecurity.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {
    private Integer idUsuario;

    private String email;

    private String nombreUsuario;

    private String contrasena;

    private Set<String> roles;
}
