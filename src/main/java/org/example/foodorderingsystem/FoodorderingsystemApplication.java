package org.example.foodorderingsystem;

import org.example.foodorderingsystem.exceptions.GlobalExceptionHandler;
import org.example.foodorderingsystem.repository.OrderRepository;
import org.example.foodorderingsystem.repository.RestaurantRepository;
import org.example.foodorderingsystem.services.OrderService;
import org.example.foodorderingsystem.services.RestaurantService;
import org.example.foodorderingsystem.services.impl.OrderServiceImpl;
import org.example.foodorderingsystem.services.impl.RestaurantServiceImpl;
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
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        OrderService orderService = new OrderServiceImpl(orderRepository, restaurantRepo);
        RestaurantService restaurantService = new RestaurantServiceImpl(restaurantRepo);

        // Onboarding Restaurants
        restaurantService.addRestaurant("R1", 5, 4.5);
        restaurantService.addRestaurant("R2", 5, 4.0);
        restaurantService.addRestaurant("R3", 1, 4.9);

//        try{
//            restaurantService.addRestaurant("",6,4.6);
//        }
//        catch (Exception e) {
//            System.out.println(globalExceptionHandler.exceptionHandler(e));
//        }

        // adding menus
        restaurantService.addMenu("R1", "Veg Biryani", 100);
        restaurantService.addMenu("R1", "Chicken Biryani", 150);
        restaurantService.addMenu("R2", "Idli", 10);
        restaurantService.addMenu("R2", "Dosa", 50);
        restaurantService.addMenu("R2", "Veg Biryani", 80);
        restaurantService.addMenu("R2", "Chicken Biryani", 175);
        restaurantService.addMenu("R3", "Idli", 15);
        restaurantService.addMenu("R3", "Dosa", 30);
        restaurantService.addMenu("R3", "Gobi Manchurian", 150);
        restaurantService.addMenu("R3", "Chicken Biryani", 175);

        restaurantService.addMenu("R1","Chicken 65",250);
        try
        {
            restaurantService.updateMenu("R2","Chicken Biryani",150);
        }
        catch (Exception e)
        {
                globalExceptionHandler.exceptionHandler(e);
        }

//        try{
//            restaurantService.updateMenu("R2","adc",45);
//        }
//        catch(Exception e)
//        {
//            System.out.println(globalExceptionHandler.exceptionHandler(e));
//        }
//
//        List<Restaurant> allRestaurants = restaurantService.getAllRestaurants();
//        System.out.println(allRestaurants);

        // Placing Orders
        try {
            Map<String, Integer> order1Items = new HashMap<>();
            order1Items.put("Idli", 3);
            order1Items.put("Dosa", 1);
            System.out.println(orderService.placeOrder("Ashwin", order1Items, new LowestCostStrategy()));
        }
        catch (Exception e)
        {
            globalExceptionHandler.exceptionHandler(e);
        }

        try{
            Map<String, Integer> order3Items = new HashMap<>();
            order3Items.put("Idli", 3);
            order3Items.put("Dosa", 1);
            System.out.println(orderService.placeOrder("Harish", order3Items, new LowestCostStrategy()));
        }
        catch (Exception e)
        {
            globalExceptionHandler.exceptionHandler(e);
        }



        try {
            Map<String, Integer> order2Items = new HashMap<>();
            order2Items.put("Veg Biryani", 3);
            order2Items.put("Dosa", 1);
            System.out.println(orderService.placeOrder("Shruthi", order2Items, new HighestRatingStrategy()));
        }
        catch (Exception e)
        {
            globalExceptionHandler.exceptionHandler(e);
        }


            // Completing Order
           // orderService.completeOrder("Ashwin");
        try{
            orderService.completeOrder("Ashwin","R3");
        }
        catch (Exception e)
        {
            globalExceptionHandler.exceptionHandler(e);
        }


        try {
            Map<String, Integer> order4Items = new HashMap<>();
            order4Items.put("Idli", 3);
            order4Items.put("Dosa", 1);
            System.out.println(orderService.placeOrder("Harish", order4Items, new LowestCostStrategy()));
        }
        catch (Exception e)
        {
            globalExceptionHandler.exceptionHandler(e);
        }


        try {
            Map<String, Integer> order5Items = new HashMap<>();
            order5Items.put("Idli", 3);
            order5Items.put("Paneer Tikka", 1);
            System.out.println(orderService.placeOrder("Diya", order5Items, new LowestCostStrategy()));
        }
        catch (Exception e)
        {
            globalExceptionHandler.exceptionHandler(e);
        }


            // Placing Another Order



    }

}
