package org.example.foodorderingsystem.exceptions;

public class OrderAssignmentException extends RuntimeException {

    public OrderAssignmentException() {
        super();
    }

    public OrderAssignmentException(String message) {
        super(message);
    }
}
