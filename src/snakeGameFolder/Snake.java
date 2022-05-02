package snakeGameFolder;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Snake extends Rectangle {
    private double pastPositionX;
    private double pastPositionY;
    public Snake(double xPos, double yPos) {
        super(xPos, yPos, SnakeGame.UNIT_SIZE, SnakeGame.UNIT_SIZE);
        this.setFillColor(new Color(51,102,0));
        // this.setStrokeColor(new Color(51,102,0));
        this.setStrokeWidth(2);
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
    
    public void removeFromCanvas(CanvasWindow canvas){
        canvas.remove(this);
    }
}
