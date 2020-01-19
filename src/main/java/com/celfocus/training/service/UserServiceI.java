package com.celfocus.training.service;

import java.util.Date;

public interface UserServiceI {

	void saveOrUpdateUser(String username, Date birthday, boolean isLegalAge);

	void deleteUser(String username);
}
