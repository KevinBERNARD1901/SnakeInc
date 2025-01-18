package com.example.snake_server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import com.example.snake_server.service.MyService;
import com.example.snake_server.model.Score;
import com.example.snake_server.model.Stat;

@RestController
public class MyController {

    @Autowired
    private MyService myService;

    // @GetMapping("/hello")
    // public String sayHello() {
    //     return myService.sayHello();
    // }

    @GetMapping("/getnumber/{number}")
    public int getnumber(@PathVariable int number, @RequestParam int increment) {
        return number+increment;
    }

    @GetMapping("/api/v1/scores")
    public ResponseEntity<List<Score>> getScores(@RequestParam String snake) {
        List<Score> scores = myService.getScores(snake);
        if (scores.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(scores); 
    }

    @GetMapping("api/v1/scores/stats")
    public List<Stat> getStats() {
        return myService.getStats();
    }


    @PostMapping("/api/v1/score")
    public ResponseEntity<Score> postScore(@RequestBody Score score) {
        switch (score.getSnake()) {
            case "python":
            case "boaConstrictor":
            case "anaconda":
                if (score.getScore() >= 0)
                    return ResponseEntity.ok(myService.postScore(score));
        
            default:
                return ResponseEntity.badRequest().build();
        }        
    }

}