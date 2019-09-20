package com.celfocus.training.repository;

import com.celfocus.training.entity.ItemInfo;

import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

    private static final List<ItemInfo> items = new ArrayList<>();

    public void createItemIfNotExists(String itemName, double itemValue) {
        ItemInfo itemInfo = findItemByName(itemName);
        if (itemInfo == null) {
            itemInfo = new ItemInfo();
            itemInfo.name = itemName;
            itemInfo.value = itemValue;
            items.add(itemInfo);
        }
    }

    public ItemInfo findItemByName(String itemName) {
        ItemInfo itemFound = null;
        for (ItemInfo item : items) {
            if (item.name.equals(itemName)) {
                itemFound = item;
            }
        }
        return itemFound;
    }


}
