package me.dio.bookshelf.services;

import me.dio.bookshelf.dto.GenreDto;

import java.util.List;

public interface CrudService<ID, T> {
    List<T> findAll();

    T findById(ID id);

    T create(T entity);

    T update(ID id, T entity);

    void delete(ID id);


}
