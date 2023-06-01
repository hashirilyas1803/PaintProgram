package Canvas;

import java.awt.*;

public class Pentagram extends Shape{
    // Variables
    protected int R;
    protected int R2;
    protected int[] x = new int[10];
    protected int[] y = new int[10];
    protected int xc;
    protected int yc;

    // Methods and Constructors
    public Pentagram(int radius, Point center, Color color, Color strokeColor, int stroke) {
        super(center, color);
        R = radius;
        R2 = (radius * 2) / 5;
        this.xc = center.x;
        this.yc = center.y;
        this.strokeColor = strokeColor;
        this.stroke = stroke;
    }
    public void pentagram() {
        int j = 0;
        for (double i = 0; i < 5; i++) {
            double b = Math.toRadians(i);
            x[j] = xc + (int) (R * Math.cos(60 + (b * 72)));
            y[j] = yc + (int) (R * Math.sin(60 + (b * 72)));
            j += 2;
        }
        j = 5;
        for (double i = 0; i < 5; i++) {
            double b = Math.toRadians(i);
            x[j % 10] = xc + (int) (R2 * Math.cos(126 + (b * 72)));
            y[j % 10] = yc + (int) (R2 * Math.sin(126 + (b * 72)));
            j += 2;
        }
    }

    @Override
    public void draw(Graphics g) {
        // Draw the shape outline
        pentagram();
        g.setColor(color);
        g.fillPolygon(x, y, 10);
        //R -= stroke;
        //R2 -= (stroke / 2);

//
//
//        // Draw the shape
//        pentagram();
//        g.setColor(color);
//        g.fillPolygon(x, y, 10);
//        R += stroke;
//        R2 += (stroke / 2);


        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(strokeColor);
        g2.setStroke(new BasicStroke(stroke));
        g2.drawPolygon(x, y, 10);
        g2.setStroke(new BasicStroke(0));

    }
}
