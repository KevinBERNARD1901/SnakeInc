package org.snakeinc.snake.model;

// Ceci est un python de base

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Snake implements AlimentEater {

    private final ArrayList<Tile> body;

    public Snake() {
        body = new ArrayList<>();
        body.add(new Tile(5, 5)); // La tête du serpent
    }

    public ArrayList<Tile> getBody() {
        return body;
    }

    public Tile getHead() {
        return body.getFirst();
    }

    public void move(char direction) {
        Tile newHead = getHead().copy();

        switch (direction) {
            case 'U':
                newHead.setY(newHead.getY() - 1);
                break;
            case 'D':
                newHead.setY(newHead.getY() + 1);
                break;
            case 'L':
                newHead.setX(newHead.getX() - 1);
                break;
            case 'R':
                newHead.setX(newHead.getX() + 1);
                break;
        }

        body.addFirst(newHead);
        body.removeLast(); // Supprime le dernier segment pour simuler le déplacement
    }

    public abstract void draw(Graphics g);

    public abstract String getName();
 

    public boolean checkSelfCollision() {
        for (int i = 1; i < body.size(); i++) {
            if (getHead().equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWallCollision() {
        return !getHead().isInsideGame();
    }

}
