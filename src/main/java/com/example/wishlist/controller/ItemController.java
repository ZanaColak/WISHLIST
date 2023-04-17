package com.example.wishlist.controller;

import com.example.wishlist.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.wishlist.services.ItemServices;

import java.util.List;

@Controller
@RequestMapping
public class ItemController {

    ItemServices itemServices;
    Item item = new Item();

    public ItemController(ItemServices itemServices) {
        this.itemServices = itemServices;
    }
    @PostMapping("user/product/save")
    public String saveProduct(@ModelAttribute Item item, Model model) {
        itemServices.addItem(item);
        List<Item> itemList = itemServices.getItemList(item.getID());
            model.addAttribute("item", itemList);
            return "item_form";
        }

    @GetMapping("user/wishlist/item")
    public String productList(Item item){
        itemServices.getItemList(item.getWishlistID());
        return "redirect:/wishList";
    }
    @GetMapping("/user/wishlist/item/delete/{id}")
    public String deleteItem(@PathVariable("id") Integer id, Model model) {
        //Delete wish ved at indtaste brugerens id (not done yet)
        model.addAttribute("wishlist", item);
        return "wishList";
    }


    @GetMapping("/user/item/update")//Find by id (Not done yet)
    public String updateItem(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("wishlist", item);
        return "wishList";
    }
}
/*     @GetMapping("/user/item/update")//Find by id (Not done yet)
    public String updateItem(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("wishlist", item);
        return "wishList";
    }*/

