package org.example.foodorderingsystem.services;

import org.example.foodorderingsystem.models.Restaurant;

import java.util.List;

public interface RestaurantService {

    void addRestaurant(String name, int maxOrders, double rating);

    void updateMenu(String restaurantName, String itemName, int price);

    void addMenu(String restaurantName, String itemName, int price);

    List<Restaurant> getAllRestaurants();

    Restaurant getOneRestaurant(String restaurantName);
}
