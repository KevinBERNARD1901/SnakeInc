package com.example.snake_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class Score {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String snake;
    private int score;

}
