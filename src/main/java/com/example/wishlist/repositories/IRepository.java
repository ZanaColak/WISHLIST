package com.example.wishlist.repositories;

import java.util.List;

public interface IRepository<T> {
    void addEntity(T obj);
    List<T> getEntities();
    T fetchEntity(int ID);
    void updateEntity(int ID, String name);
    void deleteEntity(int ID, String name);
}
