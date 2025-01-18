package com.example.snake_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.snake_server.model.Score;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
    public List<Score> findBySnake(String snake);
    
}
