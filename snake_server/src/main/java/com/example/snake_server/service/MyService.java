package com.example.snake_server.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.snake_server.repository.ScoreRepository;
import com.example.snake_server.model.Score;
import com.example.snake_server.model.Stat;

@Service
public class MyService {

    @Autowired
    private ScoreRepository scoreRepository;
    
    public String sayHello() {
        return "Hello, I'm Kévin!";
    }

    public Score postScore(Score score) {
        Score savedScore=scoreRepository.save(score);
        return savedScore;
    }

    public List<Score> getScores(String snake) {
        return scoreRepository.findBySnake(snake);
    }

    private Stat createStats(List<Score> scores, String snake) {
        Stat stat = new Stat();
        stat.setSnake(snake);
        stat.setMin(scores.stream().mapToInt(Score::getScore).min().orElse(Integer.valueOf(0)));
        stat.setMax(scores.stream().mapToInt(Score::getScore).max().orElse(Integer.valueOf(0)));
        stat.setAverage(scores.stream().mapToInt(Score::getScore).average().orElse(0));
        return stat;
    }

    public List<Stat> getStats() {
        List<Score> pythonScores = scoreRepository.findBySnake("python");
        List<Score> anacondaScores = scoreRepository.findBySnake("anaconda");
        List<Score> boaConstrictorScores = scoreRepository.findBySnake("boaConstrictor");

        
        Stat statPython = createStats(pythonScores, "python");
        Stat statAnaconda = createStats(anacondaScores, "anaconda");
        Stat statBoaConstrictor = createStats(boaConstrictorScores, "boaConstrictor");

        return List.of(statPython, statAnaconda, statBoaConstrictor);
    }
}
