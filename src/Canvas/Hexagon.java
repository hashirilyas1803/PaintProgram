package Canvas;

import java.awt.*;

public class Hexagon extends Shape{
    // Variables
    protected int R;
    protected int[] x = new int[6];
    protected int[] y = new int[6];
    protected int xc;
    protected int yc;

    // Methods and Constructors
    public Hexagon(int radius, Point center, Color color, Color strokeColor, int stroke) {
        super(center, color);
        R = radius;
        this.color = color;
        this.xc = center.x;
        this.yc = center.y;
        this.strokeColor = strokeColor;
        this.stroke = stroke;
    }
    public void hex() {
        for (double i = 0; i < 6; i++) {
           // double b = Math.toRadians(i);
            double b = Math.toRadians(60 * i + 30);
            x[(int) i] = xc + (int) (R * Math.cos(b));
            y[(int) i] = yc + (int) (R * Math.sin(b));
        }
    }

    @Override
    public void draw(Graphics g) {
        // Draw the shape outline
        hex();
        g.setColor(strokeColor);
        g.fillPolygon(x, y, 6);
        R -= stroke;

        // Draw the shape
        hex();
        g.setColor(color);
        g.fillPolygon(x, y, 6);
        R += stroke;
    }
}
