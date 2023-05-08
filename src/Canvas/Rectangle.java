package Canvas;

import java.awt.*;

public class Rectangle extends Shape{
    private int height;
    private int width;

    public Rectangle(Point center, Color color, int width, int height) {
        super(center, color);
        setWidth(width);
        setHeight(height);
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
        g.setColor(getColor());
        g.fillRect(getCenter().x, getCenter().y, getWidth(), getHeight());
    }
}
