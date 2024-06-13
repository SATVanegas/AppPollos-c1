package com.intfinity.myapi.pollonico.interfaces;

import java.util.List;

public interface RepositorioGenerico <T> {

    List<T> findAll();
    T searchById (Integer id);
    void save(T t);
    void remove(Integer id);

}
