package com.celfocus.training.repository;

import com.celfocus.training.model.ItemInfo;
import java.util.ArrayList;
import java.util.List;

public class ItemInfoRepositoryImpl implements ItemInfoRepository {

	private List<ItemInfo> itemsInfo = new ArrayList<>();

	@Override
	public ItemInfo fetchItemInfo(String itemName) {
		return itemsInfo.stream().filter(item -> item.name.equals(itemName)).findFirst().orElse(null);
	}
}
