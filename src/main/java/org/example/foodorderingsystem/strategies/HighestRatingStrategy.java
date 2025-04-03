package org.example.foodorderingsystem.strategies;



import org.example.foodorderingsystem.models.Order;
import org.example.foodorderingsystem.models.Restaurant;

import java.util.Comparator;
import java.util.List;

public class HighestRatingStrategy implements SelectionStrategy {
    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, Order order) {
        return restaurants.stream()
                .filter(r -> r.getCurrentOrders() < r.getMaxOrders())
                .max(Comparator.comparingDouble(Restaurant::getRating))
                .orElse(null);
    }

}
