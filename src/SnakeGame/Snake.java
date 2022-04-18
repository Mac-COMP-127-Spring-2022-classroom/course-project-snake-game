package SnakeGame;

import java.awt.Color;
import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class Snake extends Rectangle {

    public static double SNAKE_WIDTH = 30;

    private double xPos;
    private double yPos;

    double dX = 0.1;
    double dY = 0.1;
     
    public Snake(double xPos, double yPos) {
        super(0, 0, SNAKE_WIDTH, SNAKE_WIDTH);
        this.setCenter(xPos,yPos);
        this.xPos = xPos;
        this.yPos = yPos;
        this.setFillColor(Color.RED);


    }

    public void turnLeft(double dt) {
        xPos -= dX * dt;
        this.setCenter(xPos, yPos);


    }

    public void turnRight(double dt) {
        xPos += dX * dt;
        this.setCenter(xPos, yPos);


    }

    public void turnUp(double dt) {
        yPos -= dY * dt;
        this.setCenter(xPos, yPos);


    }

    public void turnDown(double dt) {
        yPos += dY * dt;
        this.setCenter(xPos, yPos);


    }

    public void moveByY(double dt){
        yPos += dY * dt;
        this.setCenter(xPos, yPos);
    }

    public void moveByX(double dt){
        xPos += dX * dt;
        this.setCenter(xPos, yPos);
    }

    public boolean intersectWall() {
        if (yPos + (SNAKE_WIDTH / 2) >= SnakeGame.CANVAS_HEIGHT || yPos <= 0) {
            return true;
        }
        if (xPos + (SNAKE_WIDTH / 2) >= SnakeGame.CANVAS_WIDTH || xPos <= 0) {
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
