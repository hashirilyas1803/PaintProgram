package Canvas;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.LinkedList;

public class FreeDrawing extends Shape{
    private LinkedList<Circle> drawing;
    private boolean stop;
    private int x1, y1;
    public FreeDrawing(Point center, Color color, int stroke) {
        super(center, color);
        drawing = new LinkedList<>();
        this.stroke = stroke;
        drawing.add(new Circle(stroke, center, color, null, 0));
        x1 = center.x;
        y1 = center.y;
    }

    public void freeDrawing(int x, int y) {
        if (!stop) {
            for (int i = x1, j = y1; i < x || j < y; i++, j++) {
                drawing.add(new Circle(stroke, new Point(i, j), color, null, 0));
            }
            x1 = x;
            y1 = y;
        }
    }

    public void stop() {
        stop = true;
    }
    @Override
    public void draw(Graphics g) {
        for (Circle circle : drawing) {
            circle.draw(g);
        }
    }
}
