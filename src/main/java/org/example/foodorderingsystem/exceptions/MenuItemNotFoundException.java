package org.example.foodorderingsystem.exceptions;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException() {
        super();
    }

    public MenuItemNotFoundException(String message) {

        super(message);
    }
}
