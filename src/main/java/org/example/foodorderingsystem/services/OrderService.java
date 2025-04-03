package org.example.foodorderingsystem.services;

import org.example.foodorderingsystem.repository.OrderRepository;
import org.example.foodorderingsystem.repository.RestaurantRepository;
import org.example.foodorderingsystem.strategies.SelectionStrategy;

import java.util.Map;

public interface OrderService {

    public String placeOrder(String user, Map<String, Integer> items, SelectionStrategy strategy);

    public void completeOrder(String user, String restaurantName);


}
