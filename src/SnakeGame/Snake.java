package SnakeGame;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class Snake extends Rectangle {
    ArrayList<Snake> snakeList = new ArrayList<>();

    public static double SNAKE_WIDTH = 30;

    private double centerX;
    private double centerY;
    public static Snake child;

    private int direction, pastDirection;

    // Speed is 0.1
    double dX = 0.1;
    double dY = 0.1;
     
    public Snake(double xPos, double yPos) {
        super(0, 0, SNAKE_WIDTH, SNAKE_WIDTH);
        this.setCenter(xPos,yPos);
        this.xPos = xPos;
        this.yPos = yPos;
        this.setFillColor(Color.RED);
        snakeList = new ArrayList<>();
        snakeList.add(this);

    public void setPastDirection(int pastDirection) {
        this.pastDirection = pastDirection;
    }
    public int getDirection() {
        return this.direction;
    }

    public int getPastDirection() {
        return this.pastDirection;
    }

    // Snake movement by int direction
    public void movement(double dt) {
        if (this.direction == 1) {
            this.turnRight(dt);
        }
        else if (this.direction == 2) {
            this.turnLeft(dt);
        }
        else if (this.direction ==3) {
            this.turnUp(dt);
        }
        else if (this.direction==4) {
            this.turnDown(dt);
        }
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

            Snake snakeBody = new Snake(this.getCenter().getX() + this.getWidth(), this.getCenter().getY());
            child = snakeBody;
            child.addToCanvas(canvas);
            snakeList.add(child);

            return true;
        } else if (canvas.getElementAt(point3) != null
            && canvas.getElementAt(point3).getClass() == Food.class) {

            Snake snakeBody = new Snake(this.getCenter().getX(), this.getCenter().getY() + this.getWidth());
            child = snakeBody;
            child.addToCanvas(canvas);
            snakeList.add(child);

            return true;
        } else if (canvas.getElementAt(point4) != null
            && canvas.getElementAt(point4).getClass() == Food.class) {

            Snake snakeBody = new Snake(this.getCenter().getX(), this.getCenter().getY() - this.getWidth());
            child = snakeBody;
            child.addToCanvas(canvas);
            snakeList.add(child);

            return true;
        }
        return false;
    }


    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(this);
    }

 


    // public void move(int direction, Snake child) {
    // pastDirection = direction;
    // if (direction == 1) {
    // child.turnRight(dt);
    // if (child != null) {
    // move(pastDirection, child);
    // }
    // } else if (direction == 2) {
    // child.turnLeft(dt);
    // // if (child != null) {
    // // child = child;
    // // move(pastDirection, child);
    // // }
    // } else if (direction == 3) {
    // child.turnUp(dt);
    // // if (child != null) {
    // // child = child;
    // // move(pastDirection, child);
    // // }
    // } else if (direction == 4) {
    // child.turnDown(dt);
    // // if (child != null) {
    // // child = snake;
    // // move(pastDirection, child);
    // // }
    // }
    // this.pastDirection = direction;
    // }


}
