package org.example.foodorderingsystem.exceptions;

public class UserInputException extends RuntimeException{
    public UserInputException() {
        super();
    }

    public UserInputException(String message) {
        super(message);
    }
}
