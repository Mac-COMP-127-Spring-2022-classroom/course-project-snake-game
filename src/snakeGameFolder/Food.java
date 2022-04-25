package snakeGameFolder;
import java.awt.Color;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Rectangle;

public class Food extends Ellipse{
    public Food(int xPos, int yPos) {
        super(xPos, yPos, snakeGame.UNIT_SIZE, snakeGame.UNIT_SIZE);
        this.setFillColor(Color.RED);
        this.setStrokeColor(Color.RED);
    }
}