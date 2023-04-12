package com.example.wishlist.services;

import com.example.wishlist.model.Wishlist;
import com.example.wishlist.repositories.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServices {

    WishlistRepository wishlistRepository;

    public WishlistServices(WishlistRepository wishlistRepository){
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wishlist> getWishlists(int ID) {
        return wishlistRepository.getWishlists(ID);
    }

    public Wishlist fetchWishlist(int wishlistID) {
        return wishlistRepository.fetchWishlist(wishlistID);
    }

    public void addWishlist(int ID, String name, int userID) {
        wishlistRepository.addWishlist(ID, name, userID);
    }

    public void updateWishlist(int ID, String name, int userID) {
        wishlistRepository.updateWishlist(ID, name, userID);
    }

    public void deleteWishlist(int ID) {
        wishlistRepository.deleteWishlist(ID);
    }


}
