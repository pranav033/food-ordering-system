package org.example.foodorderingsystem.repository;



import org.example.foodorderingsystem.exceptions.MenuItemNotFoundException;
import org.example.foodorderingsystem.exceptions.RestaurantNotFoundException;
import org.example.foodorderingsystem.exceptions.UserInputException;
import org.example.foodorderingsystem.models.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestaurantRepository {
    private final List<Restaurant> restaurants = new ArrayList<>();

    public void addRestaurant(String name, int maxOrders, double rating) {
        if(name==null || name.isEmpty() || maxOrders<0 || rating<0) throw new UserInputException("Please provide valid input.");
        restaurants.add(new Restaurant(name, maxOrders, rating, new HashMap<>()));
    }

    public void addMenu(String restaurantName, String itemName, int price) {

        if(restaurantName.isEmpty() ||itemName.isEmpty() || price<0) throw new UserInputException("Please enter valid inputs");

        Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getName().equalsIgnoreCase(restaurantName))
                .findFirst()
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found: " + restaurantName));


        // Menu item cannot be removed, only updated
        restaurant.getMenu().put(itemName, price);
    }


    public void updateMenu(String restaurantName, String itemName, int price) {
        Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getName().equalsIgnoreCase(restaurantName))
                .findFirst()
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found: " + restaurantName));

        if(!restaurant.getMenu().containsKey(itemName)) throw new MenuItemNotFoundException("Given menu item not found in the restaurant");
        if(price<0) throw new UserInputException("Please enter valid inputs.");
        // Menu item cannot be removed, only updated
        restaurant.getMenu().put(itemName, price);
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
