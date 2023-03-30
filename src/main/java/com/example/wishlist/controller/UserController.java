package com.example.wishlist.controller;

import com.example.wishlist.model.User;
import com.example.wishlist.repositories.DBRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserController {
    DBRepository dbRepository;

    public UserController(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    @GetMapping("/user")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

}
