package com.celfocus.training.repository.iteminfo.inmemory;

import com.celfocus.training.domain.ItemInfo;
import com.celfocus.training.repository.iteminfo.IItemInfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryItemInfoRepository implements IItemInfoRepository {

    private static final List<ItemInfo> itemList = new ArrayList<>();

    @Override
    public boolean insert(ItemInfo itemInfo) {
        return itemList.add(itemInfo);
    }

    @Override
    public Optional<ItemInfo> findItem(String name){
        return itemList.stream().filter(item -> item.getName().equals(name)).findFirst();
    }
}
