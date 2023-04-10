package com.example.wishlist.controller;

import com.example.wishlist.model.Item;
import com.example.wishlist.model.User;
import com.example.wishlist.repositories.DBRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserController {
    DBRepository dbRepository;

    public UserController(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    //http://localhost:8080/user
    @GetMapping("/user")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }
    @GetMapping("/user/signup")
    public String submitForm(@ModelAttribute("user") User user){
        System.out.println(user);
        return "signup";
    }
    @GetMapping("/user/product")
    public String submitProduct(Model model){
        Item item = new Item();
        model.addAttribute("item", item);
        return "product_form";
    }

}
