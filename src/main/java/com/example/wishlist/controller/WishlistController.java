package com.example.wishlist.controller;

import com.example.wishlist.model.Wishlist;
import com.example.wishlist.services.WishlistServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


   // @PostMapping("user/product/save") //Virker ikke/ i tvivl om det skal være en item den gemmer eller en wishlist
   // public String saveProduct(Wishlist wishlist){
       // wishlistServices.addWishlist(wishlist.getID(), wishlist.getName(), wishlist.getUserID());
        //return "redirect:/wishList";
    //}

    /*@GetMapping("")//Find by id (Not done yet)
    public String editWishlist(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("wishlist", wishlist);
        return "item_form";
    }

    @GetMapping("")
    public String deleteWish(@PathVariable("id") Integer id, Model model) {
        //Delete wish ved at indtaste brugerens id (not done yet)
        model.addAttribute("wishlist", wishlist);
        return "item_form";
    }
    *
     */
}