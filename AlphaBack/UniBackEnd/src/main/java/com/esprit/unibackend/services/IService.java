package com.esprit.unibackend.services;

import java.util.List;

public interface IService<T> {
    T Create (T session );
    T Update(T t);
    T Retrieve(int id);
    List<T> Retrieve();
    void Delete(int id);
}
