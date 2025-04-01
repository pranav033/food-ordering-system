package org.example.foodorderingsystem.services;


import org.example.foodorderingsystem.exceptions.OrderAssignmentException;
import org.example.foodorderingsystem.exceptions.OrderNotFoundException;
import org.example.foodorderingsystem.models.Order;
import org.example.foodorderingsystem.models.Restaurant;
import org.example.foodorderingsystem.repository.OrderRepository;
import org.example.foodorderingsystem.repository.RestaurantRepository;
import org.example.foodorderingsystem.strategies.SelectionStrategy;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final RestaurantRepository restaurantRepo;

    public OrderService(OrderRepository orderRepo, RestaurantRepository restaurantRepo) {
        this.orderRepo = orderRepo;
        this.restaurantRepo = restaurantRepo;
    }

    public String placeOrder(String user, Map<String, Integer> items, SelectionStrategy strategy) {
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

    public void completeOrder(String user) {
        Order order = orderRepo.getOrders().stream()
                .filter(o -> o.getUser().equals(user) && !o.isCompleted())
                .findFirst()
                .orElseThrow(() -> new OrderNotFoundException("No ongoing order found for user: " + user));

        order.setCompleted(true);
        order.getAssignedRestaurant().setCurrentOrders(order.getAssignedRestaurant().getCurrentOrders() - 1);
    }
}
