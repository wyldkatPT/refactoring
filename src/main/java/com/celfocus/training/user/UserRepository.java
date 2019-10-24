package com.celfocus.training.user;

import com.celfocus.training.util.Repository;

public interface UserRepository<S, U> extends Repository<String, User> {

    boolean userExists(String name);

}
