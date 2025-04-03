package org.example.foodorderingsystem.strategies;



import org.example.foodorderingsystem.models.Order;
import org.example.foodorderingsystem.models.Restaurant;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class HighestRatingStrategy implements SelectionStrategy {
    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, Order order) {
        return restaurants.stream()
                .filter(r -> r.getCurrentOrders() < r.getMaxOrders())
               // .filter(r -> canFulfillOrder(r, order.getItems()))
                .max(Comparator.comparingDouble(Restaurant::getRating))
                .orElse(null);
    }

    private boolean canFulfillOrder(Restaurant restaurant, Map<String, Integer> orderItems) {
        return orderItems.keySet().stream().allMatch(restaurant.getMenu()::containsKey);
    }
}
