package com.example.wishlist.model;

public class User {
    private int id;
    private String userName;
    private String email;

    private List<Wishlist> wishlists;

    public User(int ID, String name, String password, String email) {
        this.userName = name;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
