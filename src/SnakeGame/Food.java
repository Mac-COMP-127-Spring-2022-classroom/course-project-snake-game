package SnakeGame;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;


public class Food extends Ellipse {
    public static double FOOD_RADIUS = 25;

    public Food(double xPos, double yPos) {
        super(xPos, yPos, FOOD_RADIUS, FOOD_RADIUS);
        this.setFillColor(Color.PINK);


    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(this);
    }

    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(this);
    }

    


}
