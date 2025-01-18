package org.snakeinc.snake.model;

import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

import org.snakeinc.snake.GamePanel;

import lombok.Getter;

public abstract class Aliment {

    @Getter
    protected Tile position;
    protected final Random random;

    public Aliment() {
        random = new Random();
        updateLocation();
    }

    public void updateLocation() {
        position = new Tile(random.nextInt(0, (GamePanel.GAME_WIDTH / GamePanel.TILE_SIZE)),
                random.nextInt(0, (GamePanel.GAME_HEIGHT / GamePanel.TILE_SIZE)));

    }

    public void draw(Graphics g) {
        g.setColor(getColor());
                position.drawOval(g);
            }

    public abstract int beEatenBy(Snake snake, int score);
        
    protected abstract Color getColor();

    
}
