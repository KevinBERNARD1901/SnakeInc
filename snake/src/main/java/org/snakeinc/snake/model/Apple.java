package org.snakeinc.snake.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import lombok.Getter;
import org.snakeinc.snake.GamePanel;

public class Apple extends Aliment {

    @Override
    protected Color getColor() {
        return Color.RED;
    }

    public int beEatenBy(Snake snake, int score) {
        score = snake.eat(this, score);
        return score;
    }

}
