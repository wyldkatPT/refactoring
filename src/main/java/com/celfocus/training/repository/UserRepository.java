package com.celfocus.training.repository;

import com.celfocus.training.model.User;
import java.util.Date;

public interface UserRepository {

	User createUser(String userName, Date birthdayDate);
	User updateUser(User user, Date birthdayDate);
	User fetchUser(String userName);
	boolean isUserFound(String userName);
	void deleteUser(String userName);
}
