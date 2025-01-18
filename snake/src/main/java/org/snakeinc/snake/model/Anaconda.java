package org.snakeinc.snake.model;

import org.snakeinc.snake.GamePanel;
import java.awt.Color;
import java.awt.Graphics;

public class Anaconda extends Snake {

    public Anaconda() {
        super();
    }

    public String getName() {
        return "anaconda";
    }

    public void draw(Graphics g) {
        for (Tile t : super.getBody()) {
            g.setColor(Color.GRAY);
            t.drawRectangle(g);
        }
    }

    public int eat(Apple apple, int score) {
        for (int i = 0; i < 2; i++) {
            super.getBody().add(apple.getPosition());
        }
        score++;
        return(score);
    }

    public int eat(Brocoli brocoli, int score) {
        getBody().removeLast();
        score++;
        return(score);
    }
}
