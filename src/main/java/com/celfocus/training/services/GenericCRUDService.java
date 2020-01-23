package com.celfocus.training.services;

import java.util.List;

public interface CRUDService <T> {
    List<T> list();

    T find(String name);

    void saveOrUpdate(T model);

    void delete(T model);

}
