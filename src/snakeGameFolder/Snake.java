package snakeGameFolder;

import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Snake extends Rectangle{
    private double pastPositionX;
    private double pastPositionY;
    public Snake(double xPos, double yPos) {
        super(xPos, yPos, snakeGame.UNIT_SIZE, snakeGame.UNIT_SIZE);
        this.setFillColor(new Color(51,102,0));
        this.setStrokeColor(new Color(51,102,0));
    }

    // Past position group methods before each moving iteration
    public void setPastPositionX(double xPos) {
        pastPositionX = xPos;
    }

    public void setPastPositionY(double yPos) {
        pastPositionY = yPos;
    }

    public Point getPastPosition() {
        return new Point(pastPositionX, pastPositionY);
    }
    
}
