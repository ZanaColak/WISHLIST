package com.example.wishlist.controller;

import com.example.wishlist.model.Wishlist;
import com.example.wishlist.services.WishlistServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class WishlistController {
    WishlistServices wishlistServices;
    Wishlist wishlist = new Wishlist();

    public WishlistController(WishlistServices wishlistServices) {
        this.wishlistServices = wishlistServices;
    }

    @GetMapping("/user/product")
    public String submitProduct(Model model) {
        model.addAttribute("wishList", wishlist);
        return "item_form";
    }

   @PostMapping("user/item_form") //Virker ikke/ i tvivl om det skal være en item den gemmer eller en wishlist
   public String createWishlist(Wishlist wishlist){
        wishlistServices.createWishlist(wishlist);
        return "redirect:/wishList";
    }
}