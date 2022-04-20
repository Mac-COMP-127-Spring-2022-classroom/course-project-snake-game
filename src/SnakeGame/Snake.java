package SnakeGame;

import java.awt.Color;
import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class Snake extends Rectangle {
    ArrayList<Snake> snakeList = new ArrayList<>();

    public static double SNAKE_WIDTH = 30;

    private double centerX;
    private double centerY;

    double dX = 0.1;
    double dY = 0.1;

<<<<<<< HEAD
    private Snake child;
    private int pastDirection;

    // private Direction pastDirection;
=======
>>>>>>> e7816f346165d45e5a2c8577b63e240cadd0d56e

    public Snake(double xPos, double yPos) {
        super(xPos, yPos, SNAKE_WIDTH, SNAKE_WIDTH);
        this.setFillColor(Color.RED);
        snakeList = new ArrayList<>();
        snakeList.add(this);


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

<<<<<<< HEAD
=======
    public void moveByY(double dt){
        centerY += dY * dt;
        this.setCenter(centerX, centerY);
    }

    public void moveByX(double dt){
        centerX += dX * dt;
        this.setCenter(centerX, centerY);
    }

>>>>>>> e7816f346165d45e5a2c8577b63e240cadd0d56e
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
<<<<<<< HEAD
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

=======
        Point point1 = new Point(centerPoint.getX(), centerPoint.getY() - SNAKE_WIDTH/2);
        Point point2 = new Point(centerPoint.getX(), centerPoint.getY() + SNAKE_WIDTH/2);
        Point point3 = new Point(centerPoint.getX() + SNAKE_WIDTH/2, centerPoint.getY());
        Point point4 = new Point(centerPoint.getX() - SNAKE_WIDTH/2, centerPoint.getY());

        if (canvas.getElementAt(point1) != null
            && canvas.getElementAt(point1).getClass() == Food.class) {  
>>>>>>> e7816f346165d45e5a2c8577b63e240cadd0d56e
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

<<<<<<< HEAD
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
=======
 
>>>>>>> e7816f346165d45e5a2c8577b63e240cadd0d56e


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
