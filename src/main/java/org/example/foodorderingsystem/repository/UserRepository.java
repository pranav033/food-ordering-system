package org.example.foodorderingsystem.repository;

import org.example.foodorderingsystem.exceptions.UserInputException;
import org.example.foodorderingsystem.exceptions.UserNotFoundException;
import org.example.foodorderingsystem.models.Order;
import org.example.foodorderingsystem.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    public void addUser(String userName, String address, String email, String phone) {
        if(userName.isEmpty() || address.isEmpty() || email.isEmpty() || phone.isEmpty())
        {
            throw new UserInputException("Please enter valid input.");
        }
        User nu = new User(userName,address,email,phone);
        users.add(nu);
    }

    public void deleteUser(String userName)
    {
        List<User> userList = users.stream().filter(e -> !e.getUserName().equals(userName)).collect(Collectors.toList());
        if(userList.size()==users.size())
        {
            throw new UserNotFoundException("User not found.");
        }
        users = userList;
    }

    public User getOneUser(String userName)
    {
        User user = users.stream().filter(u -> u.getUserName().equals(userName)).findFirst()
                .orElse(null);

        if(user==null) throw new UserNotFoundException("User not found.");

        return user;

    }

    public List<User> getAllUsers()
    {
        return users;
    }
}
