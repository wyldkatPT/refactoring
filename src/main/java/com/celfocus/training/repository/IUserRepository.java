package com.celfocus.training.repository;

import com.celfocus.training.domain.User;

import java.util.Optional;

public interface IUserRepository {

    boolean exists(String username);

    Optional<User> findOne(String id);

    boolean insert(User user);

    int getLegalAge();

    boolean remove(User user);
}
