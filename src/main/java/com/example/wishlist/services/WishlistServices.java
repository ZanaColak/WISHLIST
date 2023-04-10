package com.example.wishlist.services;

import com.example.wishlist.model.User;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.repositories.DBRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServices {

    DBRepository dbRepository;

    public WishlistServices(DBRepository dbRepository){
        this.dbRepository = dbRepository;
    }

    public List<Wishlist> getWishlists() {
        return dbRepository.getWishlists();
    }

    public Wishlist fetchWishlist(int wishlistID) {
        return dbRepository.fetchWishlist(wishlistID);
    }


}
