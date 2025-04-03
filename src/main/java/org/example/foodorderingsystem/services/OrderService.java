package org.example.foodorderingsystem.services;

import org.example.foodorderingsystem.repository.OrderRepository;
import org.example.foodorderingsystem.repository.RestaurantRepository;
import org.example.foodorderingsystem.strategies.SelectionStrategy;

import java.util.Map;

public interface OrderService {

    String placeOrder(String user, Map<String, Integer> items, SelectionStrategy strategy);

    void completeOrder(String user, String restaurantName);


}
