package org.example.foodorderingsystem.repository;



import org.example.foodorderingsystem.exceptions.RestaurantNotFoundException;
import org.example.foodorderingsystem.models.MenuItem;
import org.example.foodorderingsystem.models.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestaurantRepository {
    private final List<Restaurant> restaurants = new ArrayList<>();

    public void addRestaurant(String name, int maxOrders, double rating) {
        restaurants.add(new Restaurant(name, maxOrders, rating, new HashMap<>()));
    }

    public void updateMenu(String restaurantName, String itemName, int price) {
        Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getName().equalsIgnoreCase(restaurantName))
                .findFirst()
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found: " + restaurantName));

        // Menu item cannot be removed, only updated
        restaurant.getMenu().put(itemName, new MenuItem(itemName, price));
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }


    public Restaurant getOneRestaurant(String restaurantName)
    {
        Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getName().equalsIgnoreCase(restaurantName))
                .findFirst()
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found: " + restaurantName));
        return restaurant;
    }
}
