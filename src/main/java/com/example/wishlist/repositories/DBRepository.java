package com.example.wishlist.repositories;

import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.wishlist.model.*;

@Repository
public class DBRepository {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public List<Wishlist> getWishlists(User user) {
        return user.getWishlists();
    }

    public void addWishlist(User user, Wishlist wishlist) {
        user.addWishlist(wishlist);
    }

}
