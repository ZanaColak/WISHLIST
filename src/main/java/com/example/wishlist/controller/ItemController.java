package com.example.wishlist.controller;

import com.example.wishlist.services.ItemServices;
import org.springframework.stereotype.Controller;

@Controller
public class ItemController {
    ItemServices itemServices;

    public ItemController(ItemServices itemServices) {
        this.itemServices = itemServices;
    }
}
