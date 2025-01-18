package com.example.snake_server.model;

import lombok.Data;

@Data
public class Stat {
    
    private String snake;
    private int min;
    private int max;
    private double average;

}