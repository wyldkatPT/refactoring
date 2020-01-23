package com.celfocus.training.controller;

import com.celfocus.training.model.ItemInfo;
import com.celfocus.training.service.ItemService;

public class ItemRequesterFrontend {

    private final ItemService itemService;

    public ItemRequesterFrontend(ItemService itemService) {
        this.itemService = itemService;
    }

    public String getFrontendItem(String type, ItemInfo item) {
        if (type.equals("html")) {
            return "<div>"
                    + "<h1>Item</h1>"
                    + "<span> " + item.getName() + "</span>"
                    + "<span> " + item.getAmount() + "</span>"
                    + "</div>";
        }
        if (type.equals("xml")) {
            return "<name> " + item.getName() + "</name>"
                    + "<valor> " + item.getAmount() + "</valor>";
        }
        return "";
    }
}
