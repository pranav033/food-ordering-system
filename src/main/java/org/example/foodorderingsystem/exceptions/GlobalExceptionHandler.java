package org.example.foodorderingsystem.exceptions;

import org.springframework.stereotype.Component;

@Component
public class GlobalExceptionHandler {


    public String exceptionHandler(Exception ex)
    {
        return ex.getClass().getSimpleName() +" occured.\nMessage -> "+ex.getMessage();
    }
}
