package com.example.wishlist.controller;

import com.example.wishlist.model.Item;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.services.ItemServices;
import com.example.wishlist.services.WishlistServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class WishlistController {
    WishlistServices wishlistServices;
    ItemServices itemServices;

    public WishlistController(WishlistServices wishlistServices, ItemServices itemServices) {
        this.wishlistServices = wishlistServices;
        this.itemServices = itemServices;
    }

    @GetMapping("/user/product")
    public String submitProduct(Model model) {
        model.addAttribute("item", new Item());
        return "item_form";
    }

    @PostMapping("/user/item_form")
    public String createWishlist(@ModelAttribute("item") Item item, Model model) {
        int wishlistId = item.getWishlistID();
        wishlistServices.createWishlist(new Wishlist(wishlistId));
        itemServices.addItem(item);
        return "redirect:/wishList?id=" + wishlistId;
    }

}
