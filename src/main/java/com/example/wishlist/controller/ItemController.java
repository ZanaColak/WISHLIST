package com.example.wishlist.controller;

import com.example.wishlist.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.wishlist.services.ItemServices;

@Controller
@RequestMapping
public class ItemController {

    ItemServices itemServices;
    Item item;

    public ItemController(ItemServices itemServices) {
        this.itemServices = itemServices;
    }
    @GetMapping("")
    public String deleteItem(@PathVariable("id") Integer id, Model model) {
        //Delete wish ved at indtaste brugerens id (not done yet)
        model.addAttribute("wishlist", item);
        return "item_form";
    }
     @GetMapping("")//Find by id (Not done yet)
    public String editItem(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("wishlist", item);
        return "item_form";
    }
}
