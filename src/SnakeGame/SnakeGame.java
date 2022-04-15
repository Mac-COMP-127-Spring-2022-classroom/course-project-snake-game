
package SnakeGame;


import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;

public class SnakeGame {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 800;
    public static CanvasWindow canvas;
    private int randomDirection = (int) (1 + Math.random() * 2);

    private Snake snake;
    private Food food;


    public SnakeGame() {
        canvas = new CanvasWindow("Snake Game", CANVAS_WIDTH, CANVAS_HEIGHT);
        createBackGround();
        startGame();


    }

    public void startGame() {
        canvas.animate(() -> {
            if(randomDirection == 1){
                snake.moveByX(20);
            }else{
                snake.moveByY(20);
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
        });

    }


    private void createBackGround() {
        snake = new Snake(100, 100);
        snake.addToCanvas(canvas);
        food = new Food((int) (Math.random() * 1000), (int) (Math.random() * 800));
        food.addToCanvas(canvas);
    }

    public static void main(String[] args) {
        new SnakeGame();


    }


}
