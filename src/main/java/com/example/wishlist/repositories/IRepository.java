package com.example.wishlist.repositories;

import java.util.List;

public interface IRepository<T> {
    void addEntity(T obj);
    List<T> getEntities();
    T fetchEntity(int ID);
    void updateEntity(T obj);
    void deleteEntity(int ID);
}
