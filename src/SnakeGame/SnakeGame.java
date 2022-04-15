
package SnakeGame;


import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;

public class SnakeGame {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 800;
    public static CanvasWindow canvas;

    private Snake snake;
    private Food food;


    public SnakeGame() {
        canvas = new CanvasWindow("Snake Game", CANVAS_WIDTH, CANVAS_HEIGHT);
        createBackGround();
        startGame();


    }

    public void startGame() {
        canvas.animate(() -> {

            // if(snake.intersectWall()){
            // canvas.closeWindow();
            // }
        });

        canvas.onKeyDown(event -> {
            if (event.getKey() == Key.RIGHT_ARROW) {
                snake.moveHorizontalRight(100);
            }
            System.out.println(snake.intersectWall());
        });

        canvas.onKeyDown(event -> {
            if (event.getKey() == Key.DOWN_ARROW) {
                snake.moveVerticalDown(100);
            }
        });

        canvas.onKeyDown(event -> {
            if (event.getKey() == Key.LEFT_ARROW) {
                snake.moveHorizontalLeft(100);
            }
        });

        canvas.onKeyDown(event -> {
            if (event.getKey() == Key.UP_ARROW) {
                snake.moveVerticalUp(100);
            }
        });

    }


    private void createBackGround() {
        snake = new Snake(100, 100);
        snake.addToCanvas(canvas);
        food = new Food((int) (Math.random() * 150), (int) (Math.random() * 100));
        food.addToCanvas(canvas);
    }

    public static void main(String[] args) {
        new SnakeGame();


    }


}
