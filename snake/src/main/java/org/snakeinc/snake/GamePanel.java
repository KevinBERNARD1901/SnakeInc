package org.snakeinc.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.GroupLayout.Alignment;

import org.snakeinc.snake.model.Apple;
import org.snakeinc.snake.model.Snake;
import org.snakeinc.snake.model.BoaConstrictor;
import org.snakeinc.snake.model.Brocoli;
import org.snakeinc.snake.model.Python;
import org.snakeinc.snake.model.Aliment;
import org.snakeinc.snake.model.Anaconda;

import lombok.Getter;
import lombok.Setter;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    public static final int TILE_SIZE = 20;
    public static final int N_TILES_X = 25;
    public static final int N_TILES_Y = 25;
    public static final int GAME_WIDTH = TILE_SIZE * N_TILES_X;
    public static final int GAME_HEIGHT = TILE_SIZE * N_TILES_Y;
    private Timer timer;
    private Snake snake;
    private Aliment aliment;
    private boolean running = false;
    private char direction = 'R';
    private String url = "http://localhost:8080";
    @Setter
    @Getter
    public int score = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        startGame();
    }

    private void startGame() {
        Random random = new Random();
        int snaketype = random.nextInt(3);
        switch (snaketype) {
            case 0:
                snake = new Anaconda();
                break;
            case 1:
                snake = new Python();
                break;
            case 2:
                snake = new BoaConstrictor();
                break;
        }
        int alimenttype = random.nextInt(2);
        switch (alimenttype) {
            case 0:
                aliment = new Apple();
                break;
            case 1:
                aliment = new Brocoli();
                break;
        }
        timer = new Timer(100, this);
        timer.start();
        running = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            aliment.draw(g);
            snake.draw(g);
            drawCounter(g);
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        sendScore(url, snake.getName(), score);
        score = 0;
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (GAME_WIDTH - metrics.stringWidth("Game Over")) / 2, GAME_HEIGHT / 2);
        g.drawString("Press Space to restart", (GAME_WIDTH - metrics.stringWidth("Press Space to restart")) / 2, 3 * GAME_HEIGHT / 4);

    }

    private void checkCollision() {
        // Vérifie si le serpent se mord ou sort de l'écran
        if (snake.checkSelfCollision() || snake.checkWallCollision()) {
            running = false;
            timer.stop();
        }
        // Vérifie si le serpent mange la pomme
        if (snake.getHead().equals(aliment.getPosition())) {
            score = aliment.beEatenBy(snake, score);
            if (snake.getBody().size() <= 0) {
                running = false;
                timer.stop();
            }
            int alimenttype = new Random().nextInt(2);
            switch (alimenttype) {
                case 0:
                    aliment = new Apple();
                    break;
                case 1:
                    aliment = new Brocoli();
                    break;
            }
            aliment.updateLocation();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move(direction);
            checkCollision();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D';
                }
                break;
            case KeyEvent.VK_SPACE:
                if (!running) {
                    startGame();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void drawCounter(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);
    }

    private void sendScore(String url, String snake, int score) {
        try{
            URI uri = new URI(url + "/api/v1/score");
            URL url_ = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url_.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            String requestBody = String.format("{\"snake\":\"%s\", \"score\":%d}", snake, score);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Score bien enregistre");
            } else {
                System.out.println("Erreur : " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
