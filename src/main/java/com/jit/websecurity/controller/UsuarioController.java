package com.jit.websecurity.controller;

import com.jit.websecurity.model.dto.UsuarioDto;
import com.jit.websecurity.model.payload.MensajeResponse;
import com.jit.websecurity.service.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/protegido")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/usuario")
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioDto usuarioDto) {
        try {
            UsuarioDto usuarioCreate = this.usuarioService.add(usuarioDto);
            return new ResponseEntity<>(
                    MensajeResponse
                            .builder()
                            .mensaje("Guardado correctamente")
                            .objeto(usuarioCreate)
                            .build()
                    , HttpStatus.CREATED
            );
        } catch (DataAccessException exDt) {
            exDt.printStackTrace();
            return new ResponseEntity<>(
                    MensajeResponse
                            .builder()
                            .mensaje(exDt.getMessage())
                            .objeto(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }

    @GetMapping(value = "/usuarios")
    public ResponseEntity<?> getAll() {
        try {
            List<UsuarioDto> empleados = this.usuarioService.getAll();
            return new ResponseEntity<>(
                    MensajeResponse
                            .builder()
                            .mensaje("")
                            .objeto(empleados)
                            .build()
                    , HttpStatus.OK
            );
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse
                            .builder()
                            .mensaje(exDt.getMessage())
                            .objeto(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        UsuarioDto usuarioDto = usuarioService.getById(id);
        if (usuarioDto != null) {
            return new ResponseEntity<>(
                    MensajeResponse
                            .builder()
                            .mensaje("")
                            .objeto(usuarioDto)
                            .build()
                    , HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    MensajeResponse
                            .builder()
                            .mensaje("No existe el registro de legajo " + id)
                            .objeto(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping(value = "/usuario/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioDto usuarioDto, @PathVariable Integer id) {
        try {
            if (this.usuarioService.existsById(id)) {
                usuarioDto.setIdUsuario(id);
                UsuarioDto usuarioUpdate = this.usuarioService.update(usuarioDto);
                return new ResponseEntity<>(
                        MensajeResponse
                                .builder()
                                .mensaje("Modificado correctamente")
                                .objeto(usuarioUpdate)
                                .build()
                        , HttpStatus.CREATED
                );
            } else {
                return new ResponseEntity<>(
                        MensajeResponse
                                .builder()
                                .mensaje("No existe el registro de legajo " + usuarioDto.getIdUsuario())
                                .objeto(null)
                                .build()
                        , HttpStatus.NOT_FOUND
                );
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse
                            .builder()
                            .mensaje(exDt.getMessage())
                            .objeto(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }

    @DeleteMapping(value = "/usuario/id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            UsuarioDto usuarioDto = this.usuarioService.delete(id);
            return new ResponseEntity<>(
                    MensajeResponse
                            .builder()
                            .mensaje("Eliminado correctamente")
                            .objeto(usuarioDto)
                            .build()
                    , HttpStatus.OK
            );
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse
                            .builder()
                            .mensaje(exDt.getMessage())
                            .objeto(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }
}
