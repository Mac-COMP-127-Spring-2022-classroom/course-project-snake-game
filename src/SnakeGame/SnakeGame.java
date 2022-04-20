
package SnakeGame;


import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;

public class SnakeGame {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 800;
    public static CanvasWindow canvas;  
    private ArrayList<Snake> snakeBody;

    private Food food;
    private double dt = 20;
    private int key_pressed;

    private Snake snake;
    private int bodyCount;


    public SnakeGame() {
        canvas = new CanvasWindow("Snake Game", CANVAS_WIDTH, CANVAS_HEIGHT);
        createBackGround();
        startGame();


    }

    public void startGame() {
        canvas.animate(() -> {

            if (key_pressed == 1) {
                snake.turnRight(dt);
            } else if (key_pressed == 2) {
                snake.turnLeft(dt);
            } else if (key_pressed == 3) {
                snake.turnUp(dt);
            } else if (key_pressed == 4) {
                snake.turnDown(dt);
            }
           
            // if(snake.intersectWall()){
            // canvas.closeWindow();
            // }
            if(snake.intersectFood(canvas)){
                food.removeFromCanvas(canvas);
                food = new Food((int) (Math.random() * 1000), (int) (Math.random() * 800));
                food.addToCanvas(canvas);
            }
        });
    

        

        canvas.onKeyDown(event -> {
            if (event.getKey() == Key.RIGHT_ARROW) {
                snake.turnRight(100);
            }
            System.out.println(snake.intersectWall());
        });

        canvas.onKeyDown(event -> {
            if (event.getKey() == Key.DOWN_ARROW) {
                snake.turnDown(100);
            }
        });

        canvas.onKeyDown(event -> {
            if (event.getKey() == Key.LEFT_ARROW) {
                snake.turnLeft(100);
            }
        });

        canvas.onKeyDown(event -> {
            if (event.getKey() == Key.UP_ARROW) {
                snake.turnUp(100);
            }
            // Transitions as food is eaten
            food_Lost_Spawn();
        });

        playerDictateMovement();
    }


    // The initial state of everything
    private void createBackGround() {
        snake = new Snake(200, 200);
        snake.addToCanvas(canvas);
        snakeBody.add(snake);
        bodyCount = 0;
        food = new Food(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
        food.addToCanvas(canvas);

        // Rectangle sky = new Rectangle(0, 0, canvas.getWidth(), canvas.getHeight());
        // sky.setFillColor(SKY_COLOR);
        // sky.setFilled(true);
        // canvas.add(sky);

        // Rectangle ground = new Rectangle(0, canvas.getHeight() - WINDOW_PADDING, canvas.getWidth(), WINDOW_PADDING);
        // ground.setFilled(true);
        // ground.setFillColor(GROUND_COLOR);
        // ground.setStroked(false);
        // canvas.add(ground);
    }

    /**
     * Spawn food at random position after food is eaten
     * Lengthens the snake
     */
    private void food_Lost_Spawn() {
        if (snake.intersectFood(canvas)) {
            // Spawn food in location
            food.removeFromCanvas(canvas);
            double xPos = Math.floor(Math.random() * (CANVAS_WIDTH * 4 / 5 - 100 + 1) + 100);
            double yPos = Math.floor(Math.random() * (CANVAS_HEIGHT * 4 / 5 - 100 + 1) + 100);
            food = new Food(xPos, yPos);
            food.addToCanvas(canvas);
        }

        food = new Food((int) (Math.random() * 1000), (int) (Math.random() * 800));
        food.addToCanvas(canvas);
    }

    public static void main(String[] args) {
        new SnakeGame();

            // Lengthen the snake
            Snake lastBody = snakeBody.get(bodyCount);
            switch (snake.getDirection()) {
                case 1:
                    snakeBody.add(new Snake(lastBody.getCenterX()-snake.getWidth(), lastBody.getCenterY()));
                    break;
            
                case 2:
                    snakeBody.add(new Snake(lastBody.getCenterX()+snake.getWidth(), lastBody.getCenterY()));
                    break;
                case 3:
                    snakeBody.add(new Snake(lastBody.getCenterX(), lastBody.getCenterY()+lastBody.getHeight()));
                    break;
                case 4:
                    snakeBody.add(new Snake(lastBody.getCenterX(), lastBody.getCenterY()-lastBody.getHeight()));
                    break;
            }
            bodyCount+=1;
            snakeBody.get(bodyCount).addToCanvas(canvas);
            snake.setPastDirection(snake.getDirection());
            snake.setDirection(key_pressed);

        }
    }

    /**
     * Captures keypads input to dictate movement of the snake
     */
    private void playerDictateMovement() {
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
            // System.out.println("Current movement:" + snake.getDirection());
            // System.out.println("Past movement:"+snake.getPastDirection());
        });
    }

    private void snakeSegmentMovement() {
        for (Snake item:snakeBody) {
            item.movement(dt);
            
        }
    }

    public static void main(String[] args) {
        new SnakeGame();
    } 

}
