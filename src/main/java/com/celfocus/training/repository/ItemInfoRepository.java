package com.celfocus.training.repository;

import com.celfocus.training.model.ItemInfo;

public interface ItemInfoRepository {

	ItemInfo fetchItemInfo(String itemName);
}
