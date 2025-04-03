package org.example.foodorderingsystem.exceptions;

import org.springframework.stereotype.Component;

@Component
public class GlobalExceptionHandler {


    public void exceptionHandler(Exception ex)
    {
        System.out.println(ex.getClass().getSimpleName() +" occured.\nMessage -> "+ex.getMessage());
    }
}
