package org.example.foodorderingsystem.repository;

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

    public void completeOrder(String user) {
        for (Order order : orders) {
            if (order.getUser().equals(user) && !order.isCompleted()) {
                order.setCompleted(true);
                order.getAssignedRestaurant().setCurrentOrders(order.getAssignedRestaurant().getCurrentOrders() - 1);
                return;
            }
        }
    }

    public List<Order> getOrders() {
        return orders;
    }
}
