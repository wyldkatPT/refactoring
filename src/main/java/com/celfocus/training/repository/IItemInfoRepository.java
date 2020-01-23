package com.celfocus.training.repository;

import com.celfocus.training.domain.ItemInfo;

import java.util.Optional;

public interface IItemInfoRepository {

    Optional<ItemInfo> findItem(String name);

    boolean insert(ItemInfo itemInfo);
}
