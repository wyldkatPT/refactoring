package com.celfocus.training.repository.user.inmemory;

import com.celfocus.training.domain.User;
import com.celfocus.training.repository.user.IUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements IUserRepository {

    private static final List<User> userList = new ArrayList<>();
    private static final int LEGAL_AGE = 18;

    @Override
    public boolean insert(User user) {
        return userList.add(user);
    }

    @Override
    public Optional<User> findByUsername(String username){
        return userList.stream().filter(user -> user.getUsername().equals(username)).findFirst();
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
