package com.celfocus.training.controller;

import com.celfocus.training.domain.User;
import com.celfocus.training.dto.UserDTO;
import com.celfocus.training.repository.user.IUserRepository;
import com.celfocus.training.util.DateUtils;

import java.util.Optional;

public class UserController {
    private IUserRepository userRepository;

    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUser(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveOrUpdateUser(UserDTO userDTO) {

        Optional<User> optionalUser = userRepository.findByUsername(userDTO.getUsername());

        return optionalUser.map(user -> updateUser(user, userDTO)).orElseGet(() -> createUser(userDTO));
    }

    private User updateUser(User user, UserDTO userDTO){

        //TODO, should be business validate this instead of passed by param?
        int legalAge = userRepository.getLegalAge();

        User userUpdated = new User(
                userDTO.getUsername(),
                DateUtils.toDate(userDTO.getBirthDate(),DateUtils.getSimpleDateFormat()),
                legalAge);

        user.setUsername(userDTO.getUsername());

        userRepository.remove(user);
        userRepository.insert(userUpdated);

        return user;
    }

    private User createUser(UserDTO userDTO) {

        //TODO, should be business validate this instead of passed by param?
        int legalAge = userRepository.getLegalAge();

        User user = new User(
                userDTO.getUsername(),
                DateUtils.toDate(userDTO.getBirthDate(),DateUtils.getSimpleDateFormat()),
                legalAge);

        userRepository.insert(user);

        return user;
    }

    public boolean deleteUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        return optionalUser.filter(user -> userRepository.remove(user)).isPresent();
    }
}
