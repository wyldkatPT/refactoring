package com.celfocus.training.repository;

import com.celfocus.training.entity.User;
import com.celfocus.training.exception.UserNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepository/* implements Repository<String> */{

	private static final List<User> users = new ArrayList<>();

	public void create(String username) {
		User user = new User();
		user.setUsername(username);
		users.add(user);
	}

	public User findUser(String username) throws UserNotFoundException{
		for (User userEntry : users) {
			if (userEntry.getUsername().equals(username)) {
				return userEntry;
			}
		}
		throw new UserNotFoundException();
	}

	public boolean userExists(String username)/* throws UserNotFoundException */{
		for (User userEntry : users) {
			if (userEntry.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
		/*throw new UserNotFoundException();*/
	}

	public User updateUser(String username, Date birthday) throws UserNotFoundException {
		for (User userEntry : users) {
			if (userEntry.getUsername().equals(username)) {
				userEntry.setUsername(username);
				updateUserBirthday(userEntry, birthday);
				return userEntry;
			}
		}
		throw new UserNotFoundException();
	}

	private void updateUserBirthday(User user, Date birthday)/* throws UserNotFoundException */{
			if (birthday != null) {
				user.setBirthday(birthday);
			}
	/*	throw new UserNotFoundException();*/
	}

	public void delete(int id) {
		users.remove(users.get(id));
	}
}
