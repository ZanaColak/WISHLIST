package com.example.wishlist.model;

import java.util.ArrayList;
import java.util.List;
import com.example.wishlist.model.Wishlist;

public class User {
    private int id;
    private String userName;
    private String password;
    private String email;

    private List<Wishlist> wishlists;

    public User(int ID, String name, String password, String email) {
        this.userName = name;
        this.password = password;
        this.email = email;
        wishlists = new ArrayList<Wishlist>();
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Wishlist> getWishlists(){
        return wishlists;
    }

    public void addWishlist(Wishlist wishlist) {
        wishlists.add(wishlist);
    }

}
