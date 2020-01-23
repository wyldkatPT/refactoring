package com.celfocus.training.service;

import com.celfocus.training.model.ItemInfo;
import com.celfocus.training.repository.ItemRepository;

public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public ItemInfo getItemByName(String itemName) {
        return repository.getItemByName(itemName);
    }

    public ItemInfo createNewItem(String name, double amount) {
       return repository.createItem(name, amount);
    }
}
