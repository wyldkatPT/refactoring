package com.celfocus.training.service;

import com.celfocus.training.entity.User;
import com.celfocus.training.exception.UserNotFoundException;
import java.util.Date;

public interface UserServiceI {

	User saveOrUpdateUser(String username, Date birthday, boolean isLegalAge) throws UserNotFoundException;

	void deleteUser(String username);
}
