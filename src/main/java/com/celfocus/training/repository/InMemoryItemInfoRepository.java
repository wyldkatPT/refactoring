package com.celfocus.training.repository;

import com.celfocus.training.domain.ItemInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryItemInfoRepository implements IItemInfoRepository {

    private static List<ItemInfo> itemList = new ArrayList<>();

    @Override
    public Optional<ItemInfo> findItem(String name){
        return itemList.stream().filter(item -> item.getName().equals(name)).findFirst();
    }

    @Override
    public boolean insert(ItemInfo itemInfo) {
        return itemList.add(itemInfo);
    }
}
