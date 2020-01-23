package com.celfocus.training.repository;

import com.celfocus.training.model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

	private List<User> users = new ArrayList<>();

	public User createUser(String userName, Date birthdayDate) {
		User user = User.builder()
				.name(userName)
				.birthdayDate(birthdayDate)
				.build();

		users.add(user);
		return user;
	}

	public User updateUser(User user, Date birthdayDate) {
		user.birthdayDate = birthdayDate;
		users.add(user);
		return user;
	}

	public boolean isUserFound(String userName) {
		return users.stream().anyMatch(user -> user.name.equals(userName));
	}

	public User fetchUser(String userName) {
		return users.stream().filter(user -> user.name.equals(userName)).findFirst().orElse(null);
	}

	public void deleteUser(String userName) {
		users.removeIf(user -> user.name.equals(userName));
	}

}