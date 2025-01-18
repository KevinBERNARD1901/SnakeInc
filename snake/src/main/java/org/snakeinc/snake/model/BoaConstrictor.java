package org.snakeinc.snake.model;
import java.awt.Color;
import java.awt.Graphics;

import lombok.Getter;

public class BoaConstrictor extends Snake {
    
        public BoaConstrictor() {
            super();
        }

        public String getName() {
            return "boaConstrictor";
        }

        public void draw(Graphics g) {
            for (Tile t : super.getBody()) {
                g.setColor(Color.BLUE);
                t.drawRectangle(g);
            }
        }

        public int eat(Apple apple, int score) {
            for (int i = 0; i < 3; i++) {
                super.getBody().add(apple.getPosition());
            }
            score++;
            return(score);
        }

        public int eat(Brocoli brocoli, int score){
            score++;   
            return(score); 
        }
}
