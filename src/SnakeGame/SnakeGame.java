
package SnakeGame;


import javax.swing.plaf.synth.SynthLabelUI;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;

public class SnakeGame {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 800;
    public static CanvasWindow canvas;
    private int randomDirection = (int) (1 + Math.random() * 2);

    private Snake snake;
    private Food food;
    private int key_pressed;
    private double dt=20;


    public SnakeGame() {
        canvas = new CanvasWindow("Snake Game", CANVAS_WIDTH, CANVAS_HEIGHT);
        createBackGround();
        startGame();


    }

    public void startGame() {
        canvas.animate(() -> {
            if (key_pressed==1) {
                snake.turnRight(dt);
            }
            else if (key_pressed==2) {
                snake.turnLeft(dt);
            }
            else if (key_pressed==3) {
                snake.turnUp(dt);
            }
            else if (key_pressed==4) {
                snake.turnDown(dt);
            }
            if (snake.intersectWall()) {
                canvas.closeWindow();
            }
            food_Lost_Spawn();
        });
        canvas.onKeyDown(event -> {
            if (event.getKey() == Key.RIGHT_ARROW) {
                key_pressed=1;
            }
            else if (event.getKey() == Key.LEFT_ARROW) {
                key_pressed=2;
            }
            else if (event.getKey() == Key.UP_ARROW) {
                key_pressed=3;
            }
            else if (event.getKey() == Key.DOWN_ARROW) {
                key_pressed=4;
            }
        });
    }


    private void createBackGround() {
        snake = new Snake(200, 200);
        snake.addToCanvas(canvas);
        food = new Food(CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
        food.addToCanvas(canvas);
    }

    /**
     * Spawn food at random position after food is eaten
     */
    private void food_Lost_Spawn() {
        if(snake.intersectFood(canvas)){
            food.removeFromCanvas(canvas);
            double xPos = Math.floor(Math.random()*(CANVAS_WIDTH*4/5-100+1)+100);
            double yPos = Math.floor(Math.random()*(CANVAS_HEIGHT*4/5-100+1)+100);
            System.out.println(xPos + " " + yPos);
            food = new Food(xPos,yPos);
            food.addToCanvas(canvas);
        }
    }

    public static void main(String[] args) {
        new SnakeGame();
    }


}
