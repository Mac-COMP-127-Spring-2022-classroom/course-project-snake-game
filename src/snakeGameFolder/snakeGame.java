package snakeGameFolder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.ui.Button;

/**
 * Snake Game presented
 * @author: Long Truong (with Tri and Thu)
 */
public class SnakeGame {
    // CANVAS SCREEN SIZE AND AN UNIT GRID SIZE
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 600;
    public static final int SCORE_PANEL_WIDTH = 200;
    public static final int TOTAL_PROGRAM_WIDTH = SCORE_PANEL_WIDTH + SCREEN_WIDTH;
    public static final int UNIT_SIZE = 25;

    // Direction variable of the snake heading
    private static char direction = 'O';

    private static int delay_time = 70; // 70ms between each iteration
    private static Food food = new Food(0, 0);
    private static Snake snakeHead;
    private static int snakeBodyCount = 1;
    private static Integer foodEaten =0;
    private static GraphicsText scoreValText;
    private static ArrayList<Snake> snakeBody = new ArrayList<>();
    public static CanvasWindow canvas;

    /**
     * Main method
     */
    public static void main(String[] args) {
        openMenu();
    }
    
    /**
     * Load the game after the play button is hit
    */
    public static void gameDisplayed() {
        setBackground();
        objectsPlaced();
        giveOutDirection();
        gameAnimate();
    }

    /** Open menu with button */
    public static void openMenu() {
        canvas = new CanvasWindow("Snake Game",SCREEN_WIDTH+ SCORE_PANEL_WIDTH, SCREEN_HEIGHT);
        GraphicsText gameTitle = new GraphicsText("SNAKE GAME");
        GraphicsText author = new GraphicsText("Presented by Long, Tri, and Thu");
        Button playButton = new Button("Play Game");
        Button quitButton = new Button("Quit Game");
        canvas.add(gameTitle);
        canvas.add(author);
        canvas.add(playButton, 200, 200);
        canvas.add(quitButton,200,300);
        gameTitle.setCenter(TOTAL_PROGRAM_WIDTH/2, SCREEN_HEIGHT /8  );
        author.setCenter(TOTAL_PROGRAM_WIDTH/2, SCREEN_HEIGHT/8 + SCREEN_HEIGHT/20);
        playButton.setCenter(TOTAL_PROGRAM_WIDTH/2, SCREEN_HEIGHT/2);
        quitButton.setCenter(TOTAL_PROGRAM_WIDTH/2, SCREEN_HEIGHT/2 + SCREEN_HEIGHT/12);
        canvas.draw();
        quitButton.onClick(() -> canvas.closeWindow());
        playButton.onClick(() -> gameDisplayed());
    }

    /**
     * Set Background with gridlines
     */
    private static void setBackground() {
        canvas = new CanvasWindow("Snake Game", SCREEN_WIDTH + SCORE_PANEL_WIDTH, SCREEN_HEIGHT);
        canvas.setBackground(Color.gray);

        for (int i = 0; i <= SCREEN_WIDTH / UNIT_SIZE; i += 1) {
            Line line = new Line(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            line.setStrokeWidth(0.5);
            canvas.add(line);
        }
        for (int i = 0; i <= SCREEN_HEIGHT / UNIT_SIZE; i += 1) {
            Line line = new Line(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            line.setStrokeWidth(0.5);
            canvas.add(line);
        }
        addScorePanel();
    }

    /**
     * Score Panel for the game
     * Consists of rectangles and textboxes
     */
    private static void addScorePanel() {
        // Score Panel is the overall interface on the rightside
        // scoreText just contains the word "YOUR SCORE". 
        // scoreValText is the text displaying the value (how many foods was eaten)
        GraphicsGroup scorePanel = new GraphicsGroup(SCREEN_WIDTH, 0);
        Rectangle panel = new Rectangle(0, 0, SCORE_PANEL_WIDTH, SCREEN_HEIGHT);
        panel.setFillColor(Color.LIGHT_GRAY);
        GraphicsText scoreText = new GraphicsText("YOUR SCORE:", 0, 0);
        scoreValText = new GraphicsText("0", 0, 0);
        scorePanel.add(panel);
        scorePanel.add(scoreText);
        scorePanel.add(scoreValText);

        scoreText.setFont(FontStyle.BOLD, 20);
        scoreText.setCenter(SCORE_PANEL_WIDTH/2, SCREEN_HEIGHT * 1/4);
        scoreValText.setFont(FontStyle.PLAIN, 40);
        scoreValText.setFillColor(Color.GRAY);
        scoreValText.setCenter(SCORE_PANEL_WIDTH/2, SCREEN_HEIGHT * 1/3);
        
        canvas.add(scorePanel);
    } 
    
    /**
     * Place food and snake head initially
     */
    private static void objectsPlaced() {
        snakeHead = new Snake(250, 250);
        // snakeHead.setStrokeColor(new Color(25, 51, 0));
        snakeHead.setFillColor(new Color(25, 51, 0));
        snakeBody.add(snakeHead);
        food = new Food(0, 0);
        spawnFood();
        canvas.add(snakeHead);
        canvas.add(food);
    }

    /**
     * Capture key input from user into variable direction. Supporting function
     */
    private static void giveOutDirection() {
        canvas.onKeyDown(event -> {
            if (event.getKey() == Key.RIGHT_ARROW && direction != 'L') {
                direction = 'R';
            }
            if (event.getKey() == Key.LEFT_ARROW && direction != 'R') {
                direction = 'L';
            }
            if (event.getKey() == Key.UP_ARROW && direction != 'D') {
                direction = 'U';
            }
            if (event.getKey() == Key.DOWN_ARROW && direction != 'U') {
                direction = 'D';
            }
        });
    }

    /**
     * Relocate food after being eaten to a random location. Supporting function
     */
    private static void spawnFood() {
        Random rand = new Random();
        int xPos = rand.nextInt((SCREEN_WIDTH / UNIT_SIZE - 0) + 0) + 0;
        int yPos = rand.nextInt((SCREEN_HEIGHT / UNIT_SIZE - 0) + 0) + 0;
        food.setPosition(xPos * UNIT_SIZE, yPos * UNIT_SIZE);
    }

    /**
     * Sprout a new body segment. Supporting function
     */
    private static void sproutBodySegment() {
        double lastX = snakeBody.get(snakeBodyCount - 1).getPosition().getX();
        double lastY = snakeBody.get(snakeBodyCount - 1).getPosition().getY();
        switch (direction) {
            case 'U':
                snakeBody.add(new Snake(lastX, lastY + UNIT_SIZE));
                break;
            case 'D':
                snakeBody.add(new Snake(lastX, lastY - UNIT_SIZE));
                break;
            case 'R':
                snakeBody.add(new Snake(lastX - UNIT_SIZE, lastY));
                break;
            case 'L':
                snakeBody.add(new Snake(lastX + UNIT_SIZE, lastY));
                break;
        }
        snakeBodyCount += 1;
        canvas.add(snakeBody.get(snakeBodyCount - 1));
    }

    /**
     * Check if the snake hits the wall
     * @return true if the snake hits the wall, false if not
     */
    private static boolean checkWallCollision() {
        if (snakeHead.getPosition().getX() >= SCREEN_WIDTH || 
            snakeHead.getPosition().getX() < 0 || 
            snakeHead.getPosition().getY() >= SCREEN_HEIGHT || 
            snakeHead.getPosition().getY() <0) {
                return true;
            } 
        else { return false; }
    }

    /**
     * Check if the snake hits its own body segment
     * @return true if it hits, false if not
     */
    private static boolean checkBodyCollision() {
        for (int i=1; i<snakeBodyCount;i++) {
            // Notice that snakeSegment.getPosition() == snakeSegment.getPosition() does not work so I have to do
            // it the longer way
            if (snakeBody.get(i).getPosition().getX() == snakeHead.getPosition().getX() &&
                snakeBody.get(i).getPosition().getY() == snakeHead.getPosition().getY()) {
                    return true;
            }
        }
        return false;
    }

    /**
     * Animate the snake movement scene. Calls spawnFood(), giveOutDirection(), and sproutBodySegment() to work.
     * If the player loses. Close the Canvas Window
     * Main-role function
     */
    private static void gameAnimate() {
        canvas.animate(() -> {
            // Check if player loses 
            if (checkWallCollision() || checkBodyCollision()) {
                canvas.closeWindow();
            }
            // Update snakeBody segment position
            int oldX = (int) snakeHead.getPosition().getX();
            int oldY = (int) snakeHead.getPosition().getY();
            wait(delay_time);
            for (int i = snakeBodyCount - 1; i > 0; i--) {
                snakeBody.get(i).setPosition(snakeBody.get(i - 1).getPosition());
            }
            snakeHead.setPastPositionX(oldX);
            snakeHead.setPastPositionY(oldY);
            // Direct the snake head
            switch (direction) {
                case 'U':
                    snakeHead.setPosition(oldX, oldY - UNIT_SIZE);
                    break;
                case 'D':
                    snakeHead.setPosition(oldX, oldY + UNIT_SIZE);
                    break;
                case 'R':
                    snakeHead.setPosition(oldX + UNIT_SIZE, oldY);
                    break;
                case 'L':
                    snakeHead.setPosition(oldX - UNIT_SIZE, oldY);
                    break;
            }
            // if snake eats food then spawn new food. 
            // Relocate food to a random position. Sprout a new body segment. Update the text for score value
            if (snakeHead.getPosition().getX() == food.getPosition().getX()
                && snakeHead.getPosition().getY() == food.getPosition().getY()) {
                foodEaten+=1;
                spawnFood();
                sproutBodySegment();
                scoreValText.setText(foodEaten.toString());
            }
        });
    }

    /**
     * A delay between animation time
     * @param ms Time input measured in ms
     */
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Losing scene 
     */
    private static void playerLost() {
        CanvasWindow canvasLose = new CanvasWindow("YOU LOSE", SCREEN_WIDTH, SCREEN_HEIGHT);
        GraphicsText loseText = new GraphicsText("YOU LOSE", 200, 200);
        loseText.setFont(FontStyle.BOLD, 200);
        canvasLose.add(loseText);
        
    }
}

