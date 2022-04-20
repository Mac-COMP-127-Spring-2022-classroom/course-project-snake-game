
package SnakeGame;


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
    private Food food;
    private double dt = 20;


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
                snake.turnRight(dt);
                if (snake.intersectFood(canvas)) {
                    addBody(snake.getX() - snake.getWidth(), snake.getY(), canvas);
                }
                moveByHead(snakeBody, key_pressed);
            } else if (key_pressed == 2) {
                snake.turnLeft(dt);
                if (snake.intersectFood(canvas)) {
                    

                }
                moveByHead(snakeBody, key_pressed);
            } else if (key_pressed == 3) {
                snake.turnUp(dt);
                if (snake.intersectFood(canvas)) {
                   

                }
                moveByHead(snakeBody, key_pressed);
            } else if (key_pressed == 4) {
                snake.turnDown(dt);
                if (snake.intersectFood(canvas)) {
                    addBody(snake.getX(), snake.getY() - snake.getWidth() , canvas);

                }
                moveByHead(snakeBody, key_pressed);

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
        });

    }


    private void createBackGround() {
        snake = new Snake(100, 100);
        snake.addToCanvas(canvas);
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

        food = new Food((int) (Math.random() * 1000), (int) (Math.random() * 800));
        food.addToCanvas(canvas);
    }

    public static void main(String[] args) {
        new SnakeGame();


    }

    public void moveByHead(ArrayList<Snake> snakeBodies, int key_pressed) {
        for (int i = 1; i < snakeBodies.size(); i++) {
            if (key_pressed == 1) {
                snakeBody.get(i).turnRight(dt);
            } else if (key_pressed == 2) {
                snakeBody.get(i).turnLeft(dt);
            } else if (key_pressed == 3) {
                snakeBody.get(i).turnUp(dt);
            } else if (key_pressed == 4) {
                snakeBody.get(i).turnDown(dt);

            }
        }
    }

    public void addBody(double posX, double posY, CanvasWindow canvas) {
        Snake snakeBody1 = new Snake(posX, posY);
        snakeBody1.addToCanvas(canvas);
        snakeBody.add(snakeBody1);
        System.out.println(snakeBody);

    }

}
