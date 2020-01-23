package com.celfocus.training.repository;

import com.celfocus.training.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
    }

    public void createUser(User user) {
        users.add(user);
    }

    public User getUser(String name) {
        return users.stream().filter(user -> user.getName().equals(name)).findFirst().orElse(null);
    }

    public User updateUser(String name, LocalDateTime birthDate, Boolean isOfLegalAge) {
        User persistedUser = getUser(name);
        if (name != null && !name.isEmpty()) {
            persistedUser.setName(name);
        }

        if (birthDate != null) {
            persistedUser.setBirthDate(birthDate);
        }

        if (isOfLegalAge != null) {
            persistedUser.setOfLegalAge(isOfLegalAge);
        }

        return persistedUser;
    }

    public void deleteUser(String name) {
        users.stream().filter(user -> user.getName().equals(name)).findFirst().ifPresent(users::remove);
    }
}
