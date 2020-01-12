package com.service.datapoa.crud;

import java.util.List;

public interface Crud<T> {
    T findById(long id);
    List<T> findAll();
    void add(T t);
    void update(long id, T t);
    void delete(long id);
}
