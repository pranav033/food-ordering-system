package org.example.foodorderingsystem.repository;

import org.example.foodorderingsystem.exceptions.OrderNotFoundException;
import org.example.foodorderingsystem.models.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void completeOrder(String user, String restaurantName) {
        Order order = orders.stream()
                .filter(o -> o.getUser().equals(user) && !o.isCompleted())
                .filter(o->o.getAssignedRestaurant().getName().equals(restaurantName) && !o.isCompleted())
                .findFirst()
                .orElseThrow(() -> new OrderNotFoundException("No ongoing order found for user: " + user));

        order.setCompleted(true);
        order.getAssignedRestaurant().setCurrentOrders(order.getAssignedRestaurant().getCurrentOrders() - 1);
    }

    public List<Order> getOrders() {
        return orders;
    }
}
