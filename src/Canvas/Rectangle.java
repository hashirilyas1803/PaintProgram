package Canvas;

import java.awt.*;

public class Rectangle extends Shape{
    private int height;
    private int width;


    public Rectangle(Point center, Color color, int width, int height, Color lineColor, int stroke) {
        super(center, color);
        setWidth(width);
        setHeight(height);
        this.color = color;
        this.strokeColor = lineColor;
        this.stroke = stroke;
        type = "Rectangle";
    }

    @Override
    public void setCenter(Point center) {
        super.setCenter(center);
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public Point getCenter() {
        return super.getCenter();
    }

    @Override
    public Color getColor() {
        return super.getColor();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void draw(Graphics g) {
        // Draw the shape
        g.setColor(strokeColor);
        g.fillRect(center.x, center.y, width, height);
        g.setColor(getColor());
        g.fillRect(center.x + stroke, center.y + stroke, width - (2 * stroke), height - (2 * stroke));
    }
}
