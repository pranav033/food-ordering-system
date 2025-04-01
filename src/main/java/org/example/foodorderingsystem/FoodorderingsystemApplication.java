package org.example.foodorderingsystem;

import org.example.foodorderingsystem.exceptions.OrderAssignmentException;
import org.example.foodorderingsystem.exceptions.OrderNotFoundException;
import org.example.foodorderingsystem.repository.OrderRepository;
import org.example.foodorderingsystem.repository.RestaurantRepository;
import org.example.foodorderingsystem.services.OrderService;
import org.example.foodorderingsystem.strategies.HighestRatingStrategy;
import org.example.foodorderingsystem.strategies.LowestCostStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class FoodorderingsystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(FoodorderingsystemApplication.class, args);

        RestaurantRepository restaurantRepo = new RestaurantRepository();
        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(orderRepository, restaurantRepo);

        // Onboarding Restaurants
        restaurantRepo.addRestaurant("R1", 5, 4.5);
        restaurantRepo.addRestaurant("R2", 5, 4.0);
        restaurantRepo.addRestaurant("R3", 1, 4.9);

        // Updating Menus
        restaurantRepo.updateMenu("R1", "Veg Biryani", 100);
        restaurantRepo.updateMenu("R1", "Chicken Biryani", 150);
        restaurantRepo.updateMenu("R2", "Idli", 10);
        restaurantRepo.updateMenu("R2", "Dosa", 50);
        restaurantRepo.updateMenu("R2", "Veg Biryani", 80);
        restaurantRepo.updateMenu("R2", "Chicken Biryani", 175);
        restaurantRepo.updateMenu("R3", "Idli", 15);
        restaurantRepo.updateMenu("R3", "Dosa", 30);
        restaurantRepo.updateMenu("R3", "Gobi Manchurian", 150);
        restaurantRepo.updateMenu("R3", "Chicken Biryani", 175);

        // Placing Orders
        try {
            Map<String, Integer> order1Items = new HashMap<>();
            order1Items.put("Idli", 3);
            order1Items.put("Dosa", 1);
            System.out.println(orderService.placeOrder("Ashwin", order1Items, new LowestCostStrategy()));

            Map<String, Integer> order2Items = new HashMap<>();
            order2Items.put("Veg Biryani", 3);
            order2Items.put("Dosa", 1);
            System.out.println(orderService.placeOrder("Shruthi", order2Items, new HighestRatingStrategy()));

            // Completing Order
            orderService.completeOrder("Ashwin");

            // Placing Another Order
            Map<String, Integer> order3Items = new HashMap<>();
            order3Items.put("Idli", 3);
            order3Items.put("Dosa", 1);
            System.out.println(orderService.placeOrder("Harish", order3Items, new LowestCostStrategy()));

        } catch (OrderAssignmentException | OrderNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
