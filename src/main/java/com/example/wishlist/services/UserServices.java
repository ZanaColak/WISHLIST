package com.example.wishlist.services;

import com.example.wishlist.model.User;
import com.example.wishlist.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    UserRepository userRepository;

    public UserServices(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User fetchUser(int userID) {
        return userRepository.fetchUser(userID);
    }

    public void addUser(int ID, String name, String password, String email) {
        userRepository.addUser(ID, name, password, email);
    }

    public void editUser(int ID, String name, String password, String email) {
        userRepository.editUser(ID, name, password, email);
    }

    public void deleteUser(int userID) {
        userRepository.deleteUser(userID);
    }

}
