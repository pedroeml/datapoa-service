package com.service.datapoa.crud;

import java.util.List;

public interface Crud<T> {
    T findById(long id);
    List<T> findAll();
    T add(T t);
    T update(long id, T t);
    T replace(long id, T t);
    T delete(long id);
}
