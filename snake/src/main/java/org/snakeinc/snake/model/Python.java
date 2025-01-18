package org.snakeinc.snake.model;

import java.awt.Color;
import java.awt.Graphics;

public class Python extends Snake {

    public String getName() {
        return "python";
    }

    public int eat(Apple apple, int score) {
        super.getBody().add(apple.getPosition());
        score++;
        return(score);
    }
    
    public int eat(Brocoli brocoli, int score) {
        for (int i = 0; i < 2; i++) {
            super.getBody().removeLast();
        }
        score++;
        return(score);
    }

    public void draw(Graphics g) {
        for (Tile t : super.getBody()) {
            g.setColor(Color.GREEN);
            t.drawRectangle(g);
        }
    }

}
