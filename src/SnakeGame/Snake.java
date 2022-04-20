package SnakeGame;

import java.awt.Color;
import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class Snake extends Rectangle {
    ArrayList<Snake> snakeList = new ArrayList<>();

    public static double SNAKE_WIDTH = 30;

    private double xPos;
    private double yPos;

    double dX = 0.1;
    double dY = 0.1;

    private Snake child;
    private int pastDirection;

    // private Direction pastDirection;

    public Snake(double xPos, double yPos) {
        super(0, 0, SNAKE_WIDTH, SNAKE_WIDTH);
        this.setCenter(xPos, yPos);
        this.xPos = xPos;
        this.yPos = yPos;
        this.setFillColor(Color.RED);
        snakeList = new ArrayList<>();
        snakeList.add(this);


    }

    public double getXpos() {
        return xPos;
    }

    public double getYpos() {
        return yPos;
    }

    public double setXpos(double xPos) {
        return this.xPos = xPos;
    }

    public double setYpos(double yPos) {
        return this.yPos = yPos;
    };


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
        Point point1 = new Point(centerPoint.getX() + SNAKE_WIDTH / 2, centerPoint.getY());
        Point point2 = new Point(centerPoint.getX() - SNAKE_WIDTH / 2, centerPoint.getY());
        Point point3 = new Point(centerPoint.getX(), centerPoint.getY() - SNAKE_WIDTH / 2);
        Point point4 = new Point(centerPoint.getX(), centerPoint.getY() + SNAKE_WIDTH / 2);

        if (canvas.getElementAt(point1) != null
            && canvas.getElementAt(point1).getClass() == Food.class) {

            Snake snakeBody = new Snake(this.getCenter().getX() - this.getWidth(), this.getCenter().getY());
            child = snakeBody;
            child.addToCanvas(canvas);
            snakeList.add(child);

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

    public void move(int direction, int size) {
        direction = pastDirection;
        int dt = 20;
        if (direction == 1) {
            snakeList.get(size).turnRight(dt);

            if ((size + 1) < snakeList.size()) {
                move(pastDirection, size + 1);
            }


        } else if (pastDirection == 2) {
            snakeList.get(size).turnLeft(dt);

            if ((size + 1) < snakeList.size()) {
                move(pastDirection, size + 1);
            }

        } else if (pastDirection == 3) {
            snakeList.get(size).turnUp(dt);

            if ((size + 1) < snakeList.size()) {
                move(pastDirection, size + 1);
            }

        } else if (direction == 4) {
            snakeList.get(size).turnDown(dt);

            if ((size + 1) < snakeList.size()) {
                move(direction, size + 1);
            }

        }
        ;

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
