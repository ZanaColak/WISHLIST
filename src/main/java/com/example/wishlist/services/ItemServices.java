package com.example.wishlist.services;

import com.example.wishlist.model.Item;
import com.example.wishlist.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServices {

    ItemRepository itemRepository;

    public ItemServices(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public List<Item> getItemList(int ID){
        return itemRepository.getItemList(ID);
    }

    public Item fetchItem(int ID) {
        return itemRepository.fetchItem(ID);
    }

    public void addItem(int ID, String name, int wishlistID) {
        itemRepository.addItem(ID, name, wishlistID);
    }

    public void updateItem(int ID, String name, int wishlistID) {
        itemRepository.updateItem(ID, name, wishlistID);
    }

    public void deleteItem(int ID) {
        itemRepository.deleteItem(ID);
    }

}
