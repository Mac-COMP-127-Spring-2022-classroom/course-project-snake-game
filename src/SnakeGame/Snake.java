package SnakeGame;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class Snake extends Rectangle {
    public static double SNAKE_LENGTH = 30;

    public static double SNAKE_WIDTH = 30;

    private double centerX;
    private double centerY;

    double dX = 0.1;
    double dY = 0.1;


    public Snake(double xPos, double yPos) {
        super(200, 200, SNAKE_LENGTH, SNAKE_WIDTH);
        this.setFillColor(Color.RED);


    }

    public void moveHorizontalLeft(double dt) {
        centerX -= dX * dt;
        this.setCenter(centerX, centerY);


    }

    public void moveHorizontalRight(double dt) {
        centerX += dX * dt;
        this.setCenter(centerX, centerY);


    }

    public void moveVerticalUp(double dt) {
        centerY -= dY * dt;
        this.setCenter(centerX, centerY);


    }

    public void moveVerticalDown(double dt) {
        centerY += dY * dt;
        this.setCenter(centerX, centerY);


    }

    public boolean intersectWall() {
        if (centerY + (SNAKE_WIDTH / 2) >= SnakeGame.CANVAS_HEIGHT || centerY <= 0) {
            return true;
        }
        if (centerX + (SNAKE_WIDTH / 2) >= SnakeGame.CANVAS_WIDTH || centerX <= 0) {
            return true;
        }
        return false;
    }

    // public boolean eatFood(){
    //     // if(CenterY)
    // }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(this);
    }


}
