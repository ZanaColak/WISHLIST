package com.example.wishlist.repositories;

import java.util.List;
import com.example.wishlist.model.*;

public interface IRepository {
    void add();
    public <T> List<T> getAll();
    void update();
    void delete();
}
