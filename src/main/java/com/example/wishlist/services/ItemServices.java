package com.example.wishlist.services;

import com.example.wishlist.model.Item;
import com.example.wishlist.repositories.DBRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServices {

    DBRepository dbRepository;

    public ItemServices(DBRepository dbRepository){
        this.dbRepository = dbRepository;
    }

    public List<Item> getItemlist(String itemName, int ID){
        return dbRepository.getItemlist(itemName, ID);
    }

}
