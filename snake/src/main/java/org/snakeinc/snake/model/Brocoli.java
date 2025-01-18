package org.snakeinc.snake.model;

import java.awt.Color;
import java.awt.Graphics;

public class Brocoli extends Aliment {

    @Override
    protected Color getColor() {
        return Color.GREEN;
    }

    public int beEatenBy(Snake snake, int score) {
        score = snake.eat(this, score);
        return score;
    }

}
