package org.example.foodorderingsystem.strategies;



import org.example.foodorderingsystem.models.Order;
import org.example.foodorderingsystem.models.Restaurant;

import java.util.Comparator;
import java.util.List;

public class LowestCostStrategy implements SelectionStrategy {
    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, Order order) {
        return restaurants.stream()
                .filter(r -> r.getCurrentOrders() < r.getMaxOrders())
                .min(Comparator.comparingInt(r -> calculateOrderCost(r, order)))
                .orElse(null);
    }

    private int calculateOrderCost(Restaurant restaurant, Order order) {
        return order.getItems().entrySet().stream()
                .mapToInt(entry -> restaurant.getMenu().get(entry.getKey()).getPrice() * entry.getValue())
                .sum();
    }
}
