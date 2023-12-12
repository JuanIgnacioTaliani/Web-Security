package com.jit.websecurity.service;

import com.jit.websecurity.model.dto.UsuarioDto;
import com.jit.websecurity.model.entity.Usuario;
import com.jit.websecurity.repository.UsuarioRepository;
import com.jit.websecurity.service.mapper.DtoMapper;
import com.jit.websecurity.service.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final EntityMapper entityMapper;

    private final DtoMapper dtoMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, EntityMapper entityMapper, DtoMapper dtoMapper) {
        this.usuarioRepository = usuarioRepository;
        this.entityMapper = entityMapper;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public UsuarioDto add(UsuarioDto usuarioDto) {
        Optional<Usuario> usuario = Optional.of(entityMapper.apply(usuarioDto));
        usuario.ifPresent(this.usuarioRepository::save);

        return usuario.map(dtoMapper).orElseThrow();
    }

    @Override
    public UsuarioDto update(UsuarioDto usuarioDto) {
        Optional<Usuario> usuario = Optional.of(entityMapper.apply(usuarioDto));
        usuario.ifPresent(this.usuarioRepository::save);

        return usuario.map(dtoMapper).orElseThrow();
    }

    @Override
    public UsuarioDto delete(Integer id) {
        if (usuarioRepository.existsById(id)) {
            UsuarioDto usuarioDto = this.getById(id);

            Usuario usuario = entityMapper.apply(usuarioDto);
            if (usuario != null) {
                this.usuarioRepository.delete(usuario);

                return usuarioDto;
            }
        }

        throw new DataAccessResourceFailureException("La entidad de usuario resultante es nula");
    }

    @Override
    public UsuarioDto getById(Integer id) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);

        return usuario.map(dtoMapper).orElse(null);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.usuarioRepository.existsById(id);
    }

    @Override
    public List<UsuarioDto> getAll() {
        List<Usuario> empleados = (List<Usuario>) this.usuarioRepository.findAll();

        return empleados.stream().map(dtoMapper).toList();
    }
}
