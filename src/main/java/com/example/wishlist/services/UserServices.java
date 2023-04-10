package com.example.wishlist.services;

import com.example.wishlist.model.User;
import com.example.wishlist.repositories.DBRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    DBRepository dbRepository;

    public UserServices(DBRepository dbRepository){
        this.dbRepository = dbRepository;
    }

    public List<User> getUsers() {
        return dbRepository.getUsers();
    }

    public User fetchUser(int userID) {
        return dbRepository.fetchUser(userID);
    }

    public void addUser(int ID, String name, String password, String email) {
        dbRepository.addUser(ID, name, password, email);
    }

    public void editUser(int ID, String name, String password, String email) {
        dbRepository.editUser(ID, name, password, email);
    }

    public void deleteUser(int userID) {
        dbRepository.deleteUser(userID);
    }

}
