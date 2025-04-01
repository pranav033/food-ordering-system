package org.example.foodorderingsystem.services;


import org.example.foodorderingsystem.models.Restaurant;
import org.example.foodorderingsystem.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    private final List<Restaurant> restaurants = new ArrayList<>();
    private final RestaurantRepository restaurantRepo;

    public RestaurantService(RestaurantRepository restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public void addRestaurant(String name, int maxOrders, double rating) {
        restaurantRepo.addRestaurant(name, maxOrders, rating);
    }

    public void updateMenu(String restaurantName, String itemName, int price) {
        restaurantRepo.updateMenu(restaurantName, itemName, price);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }
}
