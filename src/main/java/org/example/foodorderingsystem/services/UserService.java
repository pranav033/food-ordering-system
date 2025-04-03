package org.example.foodorderingsystem.services;

import org.example.foodorderingsystem.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getOneUser(String userName);

    void addUser(String userName, String address, String email, String phone);

    void deleteUser(String userName);
}
