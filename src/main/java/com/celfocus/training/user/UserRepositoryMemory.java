package com.celfocus.training.user;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryMemory implements UserRepository<String, User> {

    public static final List<User> users = new ArrayList<>();

    @Override
    public boolean userExists(String name) {
        return findById(name).isPresent();
    }

    @Override
    public Optional<User> findById(String id) {
        return users.stream().filter(user -> user.name.equals(id)).findFirst();
    }

    @Override
    public void deleteById(String id) {
        users.removeIf(user -> user.name.equals(id));
    }

    @Override
    public void add(User user) {
        if(!userExists(user.name)) {
            users.add(user);
        }
    }

    @Override
    public int count() {
        return users.size();
    }
}
