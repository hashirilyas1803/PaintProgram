package Canvas;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public abstract class Shape implements Serializable {
    protected Point center;
    protected Color color;
    protected Color strokeColor;
    public String type;
    public int stroke;


    public Shape() {
    }

    public Shape(Point center, Color color) {
        this.center = center;
        this.color = color;
    }

    public Color getStrokeColor() {
        return strokeColor;
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
