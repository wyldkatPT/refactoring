package com.celfocus.training.business;
import com.celfocus.training.model.ItemInfo;
import java.util.ArrayList;
import java.util.List;

public class ItemInfoBusiness {
    private List<ItemInfo> itemInfos = new ArrayList<>();

    public ItemInfo findItemInfo(String name) {
        ItemInfo itemFound = null;
        for (ItemInfo item : itemInfos) {
            if (item.name.equals(name)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    public boolean isExistsItemInfo(String name){
        return findItemInfo(name) != null;
    }

    public void addItemInfoIfNotExists(String name, double price) {
        if(isExistsItemInfo(name)) return;

        ItemInfo info = new ItemInfo(name,price);
        itemInfos.add(info);
    }
}
