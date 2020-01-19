package com.celfocus.training.repository;

import com.celfocus.training.entity.User;
import com.celfocus.training.exception.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

	private static final List<User> users = new ArrayList<>();

	@Override
	public void create(User user) {
		users.add(user);
	}

	@Override
	public int find(User user) throws UserNotFoundException {
		for (User userEntry : users) {
			if (userEntry.getUsername().equals(user.getUsername())) {
				return users.indexOf(userEntry);
			}
		}
		throw new UserNotFoundException();
	}

	@Override
	public void delete(int id) {
		users.remove(users.get(id));
	}
}
