package com.example.wishlist.repositories;

import com.example.wishlist.model.User;
import com.example.wishlist.model.Wishlist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("wishlist_stub")
public class StubRepository implements IRepository {
    List<Wishlist> wishlists = new ArrayList<>(List.of());

    @Override
    public List<User> getAll() {
        return null;
    }
}
