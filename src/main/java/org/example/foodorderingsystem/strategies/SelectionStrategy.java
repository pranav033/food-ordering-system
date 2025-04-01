package org.example.foodorderingsystem.strategies;



import org.example.foodorderingsystem.models.Order;
import org.example.foodorderingsystem.models.Restaurant;

import java.util.List;

public interface SelectionStrategy {
    Restaurant selectRestaurant(List<Restaurant> restaurants, Order order);
}
