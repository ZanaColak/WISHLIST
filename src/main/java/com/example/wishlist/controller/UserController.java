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
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String submitSignupForm(@ModelAttribute("user") User user) {
        userServices.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        System.out.println(model);

        return "login";
    }

    //Ikke færdig
    @PostMapping("/login")
    public String login(@RequestParam("userID") int userID, @RequestParam("email") String email, @RequestParam("password") String pwd, HttpSession httpSession, Model model) {
        User user = userServices.fetchUser(userID);
        if (user != null) {
            if (user.getEmail().equals(email) && user.getPassword().equals(pwd)) {
                model.addAttribute("user", user);
                httpSession.setAttribute("user", user);
                httpSession.setMaxInactiveInterval(60);
                return "item_form";
            }
        }
        model.addAttribute("wrongCredentials", true);
        return "login_fail";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "homepage";
    }


    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> userList = userServices.getUsers();
        model.addAttribute("userList", userList);
        return "users";
    }
}

