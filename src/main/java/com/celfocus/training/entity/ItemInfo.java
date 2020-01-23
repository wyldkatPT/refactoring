package com.celfocus.training.entity;

public class ItemInfo {

	private String itemName;
	private double itemValue;

	public ItemInfo(String itemName, double itemValue) {
		this.itemName = itemName;
		this.itemValue = itemValue;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemValue() {
		return itemValue;
	}

	public void setItemValue(double itemValue) {
		this.itemValue = itemValue;
	}
}
