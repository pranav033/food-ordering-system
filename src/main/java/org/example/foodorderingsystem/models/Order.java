package org.example.foodorderingsystem.models;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Order {
    @Override
    public String toString() {
        return "Order{" +
                "user='" + user + '\'' +
                ", items=" + items +
                ", assignedRestaurant=" + assignedRestaurant +
                ", completed=" + completed +
                '}';
    }

    private String user;
    private Map<String, Integer> items;
    private Restaurant assignedRestaurant;
    private boolean completed;

    public Order(String user, Map<String, Integer> items, Restaurant assignedRestaurant) {
        this.user = user;
        this.items = items;
        this.assignedRestaurant = assignedRestaurant;
        this.completed = false;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public Restaurant getAssignedRestaurant() {
        return assignedRestaurant;
    }

    public void setAssignedRestaurant(Restaurant assignedRestaurant) {
        this.assignedRestaurant = assignedRestaurant;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
