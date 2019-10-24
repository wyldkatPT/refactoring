package com.celfocus.training.impl;

import com.celfocus.training.models.ItemInfo;

import java.util.ArrayList;
import java.util.List;

public class ItemInfoImpl {

    private static final List<ItemInfo> itensList = new ArrayList<>();

    public ItemInfo findItem(String name) {

        ItemInfo itemFound = null;
        for (ItemInfo item : itensList) {
            if (item.getName().equals(name)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    public boolean hasItemInfo(String name) {
        return this.findItem(name) != null;
    }

    public void additemifnotexists(String name, double value) {

        if(this.hasItemInfo(name)) {
            return;
        }

        ItemInfo ift = new ItemInfo(name, value);
        itensList.add(ift);
    }

}
