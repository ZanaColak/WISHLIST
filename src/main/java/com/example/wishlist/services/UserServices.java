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

}
