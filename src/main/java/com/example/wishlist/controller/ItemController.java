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
    Item item = new Item();

    public ItemController(ItemServices itemServices) {
        this.itemServices = itemServices;
    }
    @GetMapping("/user/item/delete")
    public String deleteItem(@PathVariable("id") Integer id, Model model) {
        //Delete wish ved at indtaste brugerens id (not done yet)
        model.addAttribute("wishlist", item);
        return "item_form";
    }
     @GetMapping("/user/item/update")//Find by id (Not done yet)
    public String updateItem(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("wishlist", item);
        return "item_form";
    }
}
