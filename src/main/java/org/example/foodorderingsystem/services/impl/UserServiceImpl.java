package org.example.foodorderingsystem.services.impl;

import org.example.foodorderingsystem.models.User;
import org.example.foodorderingsystem.repository.UserRepository;
import org.example.foodorderingsystem.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getOneUser(String userName) {
        return userRepository.getOneUser(userName);
    }

    @Override
    public void addUser(String userName, String address, String email, String phone) {
        userRepository.addUser(userName,address,email,phone);
    }


    @Override
    public void deleteUser(String userName) {
        userRepository.deleteUser(userName);
    }
}
