package com.celfocus.training.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingCartItem {

	public ItemInfo itemInfo;
	public int quantity;
	public double discountAmount;
}
