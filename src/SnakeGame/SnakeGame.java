
package SnakeGame;


import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthLabelUI;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;

public class SnakeGame {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 800;
    public static CanvasWindow canvas;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    private int randomDirection = (int) (1 + Math.random() * 2);
    // private static Array
    private ArrayList<Snake> snakeBody;
    private SnakeManager snakeManager;
=======

>>>>>>> e735d6f6d9a57603a7a94aa116858ee98b50a246
=======
    private int randomDirection = (int) (1 + Math.random() * 2);

>>>>>>> parent of e735d6f (Update SnakeGame.java)
=======
    private int randomDirection = (int) (1 + Math.random() * 2);

>>>>>>> parent of e735d6f (Update SnakeGame.java)
    private Snake snake;
    private Snake child;
    private Food food;
    private int key_pressed;
    private double dt = 20;
    private int pastDirection;


    public SnakeGame() {
        canvas = new CanvasWindow("Snake Game", CANVAS_WIDTH, CANVAS_HEIGHT);
        snakeManager = new SnakeManager();
        snakeBody = new ArrayList<Snake>();
        createBackGround();
        startGame();

    }

    public void startGame() {

        canvas.animate(() -> {
            if (key_pressed == 1) {
                move(1);
                if (snake.intersectFood(canvas)) {
                    addBody(child.getCenter().getX() - snake.getWidth(), snake.getCenter().getY(), canvas);

                }
                // moveByHead(snakeBody, key_pressed);
            } else if (key_pressed == 2) {
                move(2);
                if (snake.intersectFood(canvas)) {
                    addBody(snake.getX() + snake.getWidth(), snake.getY(), canvas);

                }
                // moveByHead(snakeBody, key_pressed);
            } else if (key_pressed == 3) {
                move(3);
                if (snake.intersectFood(canvas)) {
                    addBody(snake.getX(), snake.getY() + snake.getWidth(), canvas);

                }
                // moveByHead(snakeBody, key_pressed);
            } else if (key_pressed == 4) {
                move(4);
                if (snake.intersectFood(canvas)) {
                    addBody(snake.getX(), snake.getY() - snake.getWidth(), canvas);

                }
                // moveByHead(snakeBody, key_pressed);
            }
            if (snake.intersectWall()) {
                canvas.closeWindow();
            }
            food_Lost_Spawn();
        });
        canvas.onKeyDown(event -> {
            if (event.getKey() == Key.RIGHT_ARROW) {
                key_pressed = 1;

            } else if (event.getKey() == Key.LEFT_ARROW) {
                key_pressed = 2;
            } else if (event.getKey() == Key.UP_ARROW) {
                key_pressed = 3;
            } else if (event.getKey() == Key.DOWN_ARROW) {
                key_pressed = 4;
            }
        });
    }


    private void createBackGround() {
        snake = new Snake(200, 200);
        snake.addToCanvas(canvas);
        child = snake;
        snakeBody.add(snake);
        food = new Food(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
        food.addToCanvas(canvas);
    }

    /**
     * Spawn food at random position after food is eaten
     */
    private void food_Lost_Spawn() {
        if (snake.intersectFood(canvas)) {
            food.removeFromCanvas(canvas);
            double xPos = Math.floor(Math.random() * (CANVAS_WIDTH * 4 / 5 - 100 + 1) + 100);
            double yPos = Math.floor(Math.random() * (CANVAS_HEIGHT * 4 / 5 - 100 + 1) + 100);
            System.out.println(xPos + " " + yPos);
            food = new Food(xPos, yPos);
            food.addToCanvas(canvas);
        }
    }

    public static void main(String[] args) {
        new SnakeGame();
    }


    public void addBody(double posX, double posY, CanvasWindow canvas) {
        Snake snakeBody1 = new Snake(posX, posY);
        child = snakeBody1;
        child.addToCanvas(canvas);
        snakeBody.add(child);
    }

    public void move(int direction) {
        pastDirection = direction;
        if (direction == 1) {
            snake.turnRight(dt);
            child.move(pastDirection);
        } else if (direction == 2) {
            snake.turnLeft(dt);
            child.move(pastDirection);
        } else if (direction == 3) {
            snake.turnUp(dt);
            child.move(pastDirection);
        } else if (direction == 4) {
            snake.turnDown(dt);
            child.move(pastDirection);
        }
    }


}
