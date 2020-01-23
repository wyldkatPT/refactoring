package com.celfocus.training.repository.user;

import com.celfocus.training.domain.User;
import com.celfocus.training.repository.IRepository;

import java.util.Optional;

public interface IUserRepository extends IRepository<User> {

    Optional<User> findByUsername(String id);

    int getLegalAge();

    boolean remove(User user);
}
