package com.celfocus.training.service;

public interface UserService {

	void insertUser(String userName, String birthdayDate);
	void updateUser(String userName, String birthdayDate);
	void removeUser(String userName);
	void addItem(String userName, String itemName, int quantity);
}
