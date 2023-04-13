package com.example.wishlist.model;

import java.util.ArrayList;

public class Wishlist {

    private int ID;

    private String name;

    private int userID;

    ArrayList<Item> items = new ArrayList<>();

    public Wishlist(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Wishlist() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void addItem(Item item) {
        items.add(item);
    }

}
