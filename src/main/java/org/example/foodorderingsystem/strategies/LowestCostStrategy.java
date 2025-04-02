package org.example.foodorderingsystem.strategies;



import org.example.foodorderingsystem.models.Order;
import org.example.foodorderingsystem.models.Restaurant;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class LowestCostStrategy implements SelectionStrategy {
    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, Order order) {
        return restaurants.stream()
                .filter(r -> r.getCurrentOrders() < r.getMaxOrders())
                .filter(r -> canFulfillOrder(r, order.getItems()))
                .min(Comparator.comparingInt(r -> calculateOrderCost(r, order)))
                .orElse(null);
    }

    private boolean canFulfillOrder(Restaurant restaurant, Map<String, Integer> orderItems) {
        return orderItems.keySet().stream().allMatch(restaurant.getMenu()::containsKey);
    }

    private int calculateOrderCost(Restaurant restaurant, Order order) {
        return order.getItems().entrySet().stream()
                .mapToInt(entry -> restaurant.getMenu().get(entry.getKey()).getPrice() * entry.getValue())
                .sum();
    }
}
