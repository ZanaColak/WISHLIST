package com.example.wishlist.controller;

import com.example.wishlist.model.User;
import com.example.wishlist.services.UserServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class UserController {
    UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }
    @GetMapping
    public String showWebsite(){
        return "index2";
    }
    //Ikke færdig
    @PostMapping ("/login")
    public String login(@RequestParam int uid, @RequestParam String pwd, HttpSession httpSession, Model model){
        User user = userServices.fetchUser(uid);
        if (user != null){
            if (user.getPassword().equals(pwd)){
                httpSession.setAttribute("user", user);
                httpSession.setMaxInactiveInterval(60);
                return "index";
            }
        }
        return "error";
    }

    @GetMapping("/user")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }
    @GetMapping("/user/save")
    public String saveUser(Model model){
        List<User> userList = userServices.getUsers();
        model.addAttribute("userList", userList);
        return "users";
    }
    @GetMapping("/user/signup")
    public String submitForm(@ModelAttribute("user") User user){
        System.out.println(user);
        return "signup";
    }
}
