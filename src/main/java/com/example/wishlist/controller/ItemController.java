package com.example.wishlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.wishlist.services.ItemServices;

@Controller
public class ItemController {

    ItemServices itemServices;

    public ItemController(ItemServices itemServices) {
        this.itemServices = itemServices;
    }








}
