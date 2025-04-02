package org.example.foodorderingsystem.models;


import org.springframework.stereotype.Component;

@Component
public class MenuItem {
    private String name;
    private int price;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public MenuItem() {
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
