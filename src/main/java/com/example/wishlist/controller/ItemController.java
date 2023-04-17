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

    private final ItemServices itemServices;

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
    public String productList(Model model, @RequestParam("wishlistId") Long wishlistId) {
        List<Item> itemList = itemServices.getItemList(Math.toIntExact(wishlistId));
        model.addAttribute("itemList", itemList);
        model.addAttribute("wishlistId", wishlistId);
        return "wishList";
    }

    @GetMapping("/user/item/{id}/update")
    public String updateItem(@PathVariable("id") int itemId, Model model) {
        Item item = (Item) itemServices.getItemList(itemId);
        model.addAttribute("item", item);
        return "item_form";
    }

    @PostMapping("/user/item/{id}/update")
    public String updateItem(@PathVariable("id") int itemId, @ModelAttribute Item item, Model model) {
        itemServices.updateItem(itemId, item.getName(), item.getWishlistID());
        return "redirect:/user/wishlist/item?wishlistId=" + item.getWishlistID();
    }

    @GetMapping("/user/item/{id}/delete")
    public String deleteItem(@PathVariable("id") int itemId, Model model) {
        Item item = (Item) itemServices.getItemList(itemId);
        int wishlistId = item.getWishlistID();
        itemServices.deleteItem(itemId);
        return "redirect:/user/wishlist/item?wishlistId=" + wishlistId;
    }

}
