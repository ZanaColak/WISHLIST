package com.example.wishlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.wishlist.services.WishlistServices;

@Controller
public class WishlistController {

    WishlistServices wishlistServices;

    public WishlistController(WishlistServices wishlistServices) {
        this.wishlistServices = wishlistServices;
    }









}
