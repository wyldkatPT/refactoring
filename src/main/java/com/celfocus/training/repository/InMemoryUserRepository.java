package com.celfocus.training.repository;

import com.celfocus.training.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements IUserRepository {

    private static List<User> userList = new ArrayList<>();
    private static final int LEGAL_AGE = 18;

    @Override
    public boolean exists(String username){
        return userList.stream().anyMatch(user -> user.getUsername().equals(username));
    }

    @Override
    public Optional<User> findOne(String username){
        return userList.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    public boolean insert(User user) {
        return userList.add(user);
    }

    @Override
    public int getLegalAge() {
        return LEGAL_AGE;
    }

    @Override
    public boolean remove(User user) {
        return userList.remove(user);
    }
}
