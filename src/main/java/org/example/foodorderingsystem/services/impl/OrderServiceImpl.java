package org.example.foodorderingsystem.services.impl;


import org.example.foodorderingsystem.exceptions.OrderAssignmentException;
import org.example.foodorderingsystem.exceptions.UserNotFoundException;
import org.example.foodorderingsystem.models.Order;
import org.example.foodorderingsystem.models.Restaurant;
import org.example.foodorderingsystem.models.User;
import org.example.foodorderingsystem.repository.OrderRepository;
import org.example.foodorderingsystem.repository.RestaurantRepository;
import org.example.foodorderingsystem.repository.UserRepository;
import org.example.foodorderingsystem.services.OrderService;
import org.example.foodorderingsystem.strategies.SelectionStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepo;
    private final RestaurantRepository restaurantRepo;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepo, RestaurantRepository restaurantRepo, UserRepository userRepository) {
        this.orderRepo = orderRepo;
        this.restaurantRepo = restaurantRepo;
        this.userRepository = userRepository;
    }

    public String placeOrder(String user, Map<String, Integer> items, SelectionStrategy strategy) {

        List<User> allUsers = userRepository.getAllUsers();
        User user1 = allUsers.stream().filter(u -> u.getUserName().equals(user)).findAny()
                .orElse(null);
        if(user1==null) throw new UserNotFoundException("User not found");
        // Ensure all items are available in a single restaurant
        Restaurant assignedRestaurant = strategy.selectRestaurant(restaurantRepo.getAllRestaurants(), new Order(user, items, null));

        if (assignedRestaurant == null)
            throw new OrderAssignmentException("Cannot assign the order. No restaurant can fulfill the entire order.");

        if (assignedRestaurant.getCurrentOrders() >= assignedRestaurant.getMaxOrders())
            throw new OrderAssignmentException("Restaurant " + assignedRestaurant.getName() + " is at full capacity.");

        assignedRestaurant.setCurrentOrders(assignedRestaurant.getCurrentOrders() + 1);
        Order order = new Order(user, items, assignedRestaurant);
        orderRepo.addOrder(order);

        return "Order assigned to " + assignedRestaurant.getName();
    }

    public void completeOrder(String user, String restaurantName) {
       orderRepo.completeOrder(user,restaurantName);
        List<User> allUsers = userRepository.getAllUsers();
        User user1 = allUsers.stream().filter(u -> u.getUserName().equals(user)).findAny()
                .orElse(null);
        if(user1==null) throw new UserNotFoundException("User not found");
    }
}
