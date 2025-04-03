package org.example.foodorderingsystem.services.impl;


import org.example.foodorderingsystem.models.Restaurant;
import org.example.foodorderingsystem.repository.RestaurantRepository;
import org.example.foodorderingsystem.services.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepo;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public void addRestaurant(String name, int maxOrders, double rating) {
        restaurantRepo.addRestaurant(name, maxOrders, rating);
    }

    public void updateMenu(String restaurantName, String itemName, int price) {
        restaurantRepo.updateMenu(restaurantName, itemName, price);
    }

    public void addMenu(String restaurantName, String itemName, int price) {
        restaurantRepo.addMenu(restaurantName, itemName, price);
    }


    public List<Restaurant> getAllRestaurants() {
        return restaurantRepo.getAllRestaurants();
    }

    public Restaurant getOneRestaurant(String restaurantName)
    {
        return restaurantRepo.getOneRestaurant(restaurantName);
    }
}
