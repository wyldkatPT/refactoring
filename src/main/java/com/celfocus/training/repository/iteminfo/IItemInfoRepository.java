package com.celfocus.training.repository.iteminfo;

import com.celfocus.training.domain.ItemInfo;
import com.celfocus.training.repository.IRepository;

import java.util.Optional;

public interface IItemInfoRepository extends IRepository<ItemInfo> {

    Optional<ItemInfo> findItem(String name);

}
