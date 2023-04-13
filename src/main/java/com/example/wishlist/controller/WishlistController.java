package com.example.wishlist.controller;

import com.example.wishlist.model.Item;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.services.WishlistServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WishlistController {
    WishlistServices wishlistServices;

    public WishlistController(WishlistServices wishlistServices) {
        this.wishlistServices = wishlistServices;
    }
    @GetMapping("/user/product")
    public String submitProduct(Model model){
        Wishlist wishlist = new Wishlist();
        model.addAttribute("wishList", wishlist);
        return "product_form";
    }
    /*@PostMapping("user/product/save") Virker ikke/ i tvivl om det skal være en item den gemmer eller en wishlist
    public String saveProduct(Wishlist wishlist){
        wishlistServices.addWishlist(wishlist);
        return "redirect:/product_form";
    }
*/
}
