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
    public String showHomepage() {
        return "homepage";
    }


    @GetMapping("/user/signup")
    public String submitForm(@ModelAttribute("user") User user) {
        System.out.println(user.getEmail()); //not done yet
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getId());

        //userServices.createUser(user);
        return "signup";
    }

    @GetMapping("/user")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        System.out.println(model);

        return "login";
    }

    //Ikke færdig
    @PostMapping("/user")
    public String login(@RequestParam int uid, @RequestParam String email, @RequestParam String pwd, HttpSession httpSession, Model model) {
        User user = userServices.fetchUser(uid);
        if (user != null) {
            if (user.getEmail().equals(email) && user.getPassword().equals(pwd)) {
                model.addAttribute("user", user);
                httpSession.setAttribute("user", user);
                httpSession.setMaxInactiveInterval(60);
                return "redirect:/item_form";
            }
        }
        return "redirect:/login_fail";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> userList = userServices.getUsers();
        model.addAttribute("userList", userList);
        return "redirect:/users";
    }
}

