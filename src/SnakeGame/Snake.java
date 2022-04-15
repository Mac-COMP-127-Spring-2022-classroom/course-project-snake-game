package SnakeGame;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class Snake extends Rectangle {

    public static double SNAKE_WIDTH = 30;

    private double centerX;
    private double centerY;

    double dX = 0.1;
    double dY = 0.1;


    public Snake(double xPos, double yPos) {
        super(xPos, yPos, SNAKE_WIDTH, SNAKE_WIDTH);
        this.setFillColor(Color.RED);


    }

    public void turnLeft(double dt) {
        centerX -= dX * dt;
        this.setCenter(centerX, centerY);


    }

    public void turnRight(double dt) {
        centerX += dX * dt;
        this.setCenter(centerX, centerY);


    }

    public void turnUp(double dt) {
        centerY -= dY * dt;
        this.setCenter(centerX, centerY);


    }

    public void turnDown(double dt) {
        centerY += dY * dt;
        this.setCenter(centerX, centerY);


    }

    public void moveByY(double dt){
        centerY += dY * dt;
        this.setCenter(centerX, centerY);
    }

    public void moveByX(double dt){
        centerX += dX * dt;
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

    public boolean intersectFood(CanvasWindow canvas) {
        Point centerPoint = this.getCenter();
        Point point1 = new Point(centerPoint.getX(), centerPoint.getY() - SNAKE_WIDTH/2);
        Point point2 = new Point(centerPoint.getX(), centerPoint.getY() + SNAKE_WIDTH/2);
        Point point3 = new Point(centerPoint.getX() + SNAKE_WIDTH/2, centerPoint.getY());
        Point point4 = new Point(centerPoint.getX() - SNAKE_WIDTH/2, centerPoint.getY());

        if (canvas.getElementAt(point1) != null
            && canvas.getElementAt(point1).getClass() == Food.class) {
            return true;
        } else if (canvas.getElementAt(point2) != null
            && canvas.getElementAt(point2).getClass() == Food.class) {
            return true;
        } else if (canvas.getElementAt(point3) != null
            && canvas.getElementAt(point3).getClass() == Food.class) {
            return true;
        } else if (canvas.getElementAt(point4) != null
            && canvas.getElementAt(point4).getClass() == Food.class) {
            return true;
        }
        return false;
    }


    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(this);
    }


}
