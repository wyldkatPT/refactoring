package com.celfocus.training.controller;

import com.celfocus.training.domain.ItemInfo;
import com.celfocus.training.repository.iteminfo.IItemInfoRepository;

import java.util.Optional;

public class ItemInfoController {

    private IItemInfoRepository infoRepository;

    public ItemInfoController(IItemInfoRepository itemInfoRepository) {
        this.infoRepository = itemInfoRepository;
    }

    public Optional<ItemInfo> findItem(String itemName) {
        return this.infoRepository.findItem(itemName);
    }

    public void addItemInfo(ItemInfo itemInfo) {
        this.infoRepository.insert(itemInfo);
    }
}
