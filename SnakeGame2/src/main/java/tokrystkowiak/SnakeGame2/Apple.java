package tokrystkowiak.SnakeGame2;

import java.awt.*;

public class Apple extends Rectangle {
    private Color color;
    public Apple(int x, int y, int width, int height,Color color){
        super(x,y,width,height);
        this.color = color;
    }
    public void paint(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fill(this);
    }
}
