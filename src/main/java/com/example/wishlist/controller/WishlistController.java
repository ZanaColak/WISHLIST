package com.example.wishlist.controller;

import com.example.wishlist.model.Item;
import com.example.wishlist.services.WishlistServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WishlistController {
    WishlistServices wishlistServices;

    public WishlistController(WishlistServices wishlistServices) {
        this.wishlistServices = wishlistServices;
    }
    @GetMapping("/user/product")
    public String submitProduct(Model model){
        Item item = new Item();
        model.addAttribute("item", item);
        return "product_form";
    }

}
