package com.celfocus.training.repository;

import com.celfocus.training.model.ItemInfo;

import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

    private final List<ItemInfo> items = new ArrayList<>();

    public ItemInfo getItemByName(String itemName) {
        return items.stream().filter(item -> item.getName().equals(itemName)).findFirst().orElse(null);
    }

    public ItemInfo createItem(String name, double amount) {
        if (getItemByName(name) == null) {
            ItemInfo itemInfo = new ItemInfo(name, amount);
            items.add(itemInfo);
            return itemInfo;
        }
        System.out.println("The item already exists");
        return null;
    }

}
