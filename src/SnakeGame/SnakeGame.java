
package SnakeGame;


import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;

public class SnakeGame {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 800;
    public static CanvasWindow canvas;

    private int randomDirection = (int) (1 + Math.random() * 2);
    // private static Array
    private ArrayList<Snake> snakeBody;
    private SnakeManager snakeManager;


    private Snake snake;
    public static Snake child;
    private Food food;
    private int key_pressed;



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
                snake.move(1,0);
                if (snake.intersectFood(canvas)) {
                

                }
                // moveByHead(snakeBody, key_pressed);
            } else if (key_pressed == 2) {
                snake.move(2,0);
                if (snake.intersectFood(canvas)) {
                    

                }
                // moveByHead(snakeBody, key_pressed);
            } else if (key_pressed == 3) {
                snake.move(3,0);
                if (snake.intersectFood(canvas)) {
                   

                }
                // moveByHead(snakeBody, key_pressed);
            } else if (key_pressed == 4) {
                snake.move(4,0);
                if (snake.intersectFood(canvas)) {
                 

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
     * Spawn food at random position after food is eaten Lengthen the snake
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





}
