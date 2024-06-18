package com.intfinity.myapi.pollonico.interfaces;

import java.util.List;

public interface RepositorioGenerico <T> {

    List<T> findAll();
    void searchById (Integer id);
    void rObjectId (T t);
    void save(T t);
    void remove(Integer id);

}
