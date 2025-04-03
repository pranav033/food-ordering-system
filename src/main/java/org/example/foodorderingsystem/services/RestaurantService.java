package org.example.foodorderingsystem.services;

import org.example.foodorderingsystem.models.Restaurant;

import java.util.List;

public interface RestaurantService {

    public void addRestaurant(String name, int maxOrders, double rating);

    public void updateMenu(String restaurantName, String itemName, int price);

    public void addMenu(String restaurantName, String itemName, int price);

    public List<Restaurant> getAllRestaurants();

    public Restaurant getOneRestaurant(String restaurantName);
}
