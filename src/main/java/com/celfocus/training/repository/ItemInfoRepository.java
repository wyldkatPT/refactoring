package com.celfocus.training.repository;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.exception.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ItemInfoRepository implements Repository<ItemInfo> {

	private static final List<ItemInfo> itemInfos = new ArrayList<>();

	@Override
	public void create(ItemInfo itemInfo) {
		itemInfos.add(itemInfo);
	}

	@Override
	public int find(ItemInfo itemInfo) throws UserNotFoundException {
		for (ItemInfo itemInfoEntry : itemInfos) {
			if (itemInfoEntry.getItemName().equals(itemInfo.getItemName())) {
				return itemInfos.indexOf(itemInfoEntry);
			}
		}
		throw new UserNotFoundException();
	}

	@Override
	public void delete(int id) {
		itemInfos.remove(itemInfos.get(id));
	}
}
