package Default;

import java.awt.*;

public class Rectangle {

    // Variables
    protected Point centre;
    protected int width;
    protected int height;
    Color rectColor;
    Color lineColor;
    Color textColor;
    int stroke;

    // Methods

    public Rectangle(int x, int y, int width, int height, Color rectColor, Color lineColor, int stroke) {
        this.centre = new Point(x, y);
        this.width = width;
        this.height = height;
        this.rectColor = rectColor;
        this.lineColor = lineColor;
        this.stroke = stroke;
    }

    public Point getCentre() {
        return centre;
    }

    public Color getRectColor() {
        return rectColor;
    }

    public void setRectColor(Color rectColor) {
        this.rectColor = rectColor;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void paint(Graphics g) {
        // Draw the shape
        g.setColor(lineColor);
        g.fillRect(centre.x, centre.y, width, height);
        g.setColor(rectColor);
        g.fillRect(centre.x + stroke, centre.y + stroke, width - (2 * stroke), height - (2 * stroke));
    }
}