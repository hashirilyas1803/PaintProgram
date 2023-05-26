package Canvas;

import java.awt.*;

public class Triangle extends Shape{
    Point corner1;
    Point corner2;
    public Triangle(Point center,Point corner1, Point corner2, Color color, Color strokeColor, int stroke) {
        super(center, color);
        setCorner1(corner1);
        setCorner2(corner2);
        type = "Triangle";
        this.strokeColor = strokeColor;
        this.stroke = stroke;
    }

    @Override
    public Point getCenter() {
        return super.getCenter();
    }

    @Override
    public void setCenter(Point center) {
        super.setCenter(center);
    }

    @Override
    public Color getColor() {
        return super.getColor();
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
    }

    public Point getCorner1() {
        return corner1;
    }

    public void setCorner1(Point corner1) {
        this.corner1 = corner1;
    }

    public Point getCorner2() {
        return corner2;
    }

    public void setCorner2(Point corner2) {
        this.corner2 = corner2;
    }


    @Override
    public void draw(Graphics g) {
        // Draw the shape outline
        int[] x = {center.x, corner1.x, corner2.x};
        int[] y = {center.y, corner1.y, corner2.y};
        g.setColor(strokeColor);
        g.fillPolygon(x, y, 3);

        // Draw the shape
        int[] x1 = {center.x, corner1.x - stroke, corner2.x + stroke};
        int[] y1 = {center.y + stroke, corner1.y - stroke / 2, corner2.y - stroke / 2};
        g.setColor(color);
        g.fillPolygon(x1, y1, 3);
    }
}
