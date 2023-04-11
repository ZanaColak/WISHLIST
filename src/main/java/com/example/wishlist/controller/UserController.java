package com.example.wishlist.controller;

import com.example.wishlist.model.Item;
import com.example.wishlist.model.User;
import com.example.wishlist.repositories.DBRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UserController {
    DBRepository dbRepository;

    public UserController(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    //Ikke færdig
    @PostMapping ("/login")
    public String login(@RequestParam int uid, @RequestParam String pwd, HttpSession httpSession, Model model){
        User user = dbRepository.fetchUser(uid);
        if (user != null){
            if (user.getPassword().equals(pwd)){
                httpSession.setAttribute("user", user);
                httpSession.setMaxInactiveInterval(60);
                return "user1";
            }
        }
        return "error";
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
