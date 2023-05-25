package Canvas;

import java.awt.*;

public abstract class Shape {
    protected Point center;
    protected Color color;
    public String type;

    public Shape(Point center, Color color) {
        this.center = center;
        this.color = color;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    abstract public void draw(Graphics g);
}
