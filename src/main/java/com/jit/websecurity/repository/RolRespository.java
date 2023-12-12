package com.jit.websecurity.repository;

import com.jit.websecurity.model.entity.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRespository extends CrudRepository<Rol, Integer> {
}
