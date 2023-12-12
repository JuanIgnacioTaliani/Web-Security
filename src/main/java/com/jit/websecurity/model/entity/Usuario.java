package com.jit.websecurity.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Email
    @NotBlank
    @Size(max = 80)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(max = 30)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @NotBlank
    @Column(name = "contrasena")
    private String contrasena;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Rol.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "roles_por_usuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles;
}
