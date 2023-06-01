package ru.aston.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<E, K extends Serializable> {

    E save(E entity);

    Optional<E> findById(K id);

    List<E> findAll();

    void update(E entity);

    void delete(E entity);

}
