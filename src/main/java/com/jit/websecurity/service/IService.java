package com.jit.websecurity.service;

import java.util.List;

public interface IService<T, ID> {
    T add(T entity);

    T update(T entity);

    T delete(ID id);

    T getById(ID id);

    boolean existsById(ID id);

    List<T> getAll();
}
