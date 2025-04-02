package org.example.foodorderingsystem.models;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Restaurant {
    private String name;
    private int maxOrders;
    private double rating;
    private Map<String, Integer> menu;
    private int currentOrders;

    public Restaurant() {
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", maxOrders=" + maxOrders +
                ", rating=" + rating +
                ", menu=" + menu +
                ", currentOrders=" + currentOrders +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxOrders() {
        return maxOrders;
    }

    public void setMaxOrders(int maxOrders) {
        this.maxOrders = maxOrders;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Map<String, Integer> getMenu() {
        return menu;
    }

    public void setMenu(Map<String, Integer> menu) {
        this.menu = menu;
    }

    public int getCurrentOrders() {
        return currentOrders;
    }

    public void setCurrentOrders(int currentOrders) {
        this.currentOrders = currentOrders;
    }

    public Restaurant(String name, int maxOrders, double rating, Map<String, Integer> menu) {
        this.name = name;
        this.maxOrders = maxOrders;
        this.rating = rating;
        this.menu = menu;
        this.currentOrders = 0;
    }
}
