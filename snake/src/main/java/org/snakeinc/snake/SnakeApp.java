package org.snakeinc.snake;

import javax.swing.JFrame;

public class SnakeApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Inc");
        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}