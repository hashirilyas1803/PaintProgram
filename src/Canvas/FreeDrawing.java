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
            drawing.add(new Circle(stroke, new Point(x, y), color, null, 0));
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

        // Only for aesthetic purposes, the actual drawing is being down by the linkedlist of circles
        for (int i = 0; i < drawing.size()-1; i++) {
            int x1 = drawing.get(i).getCenter().x;
            int y1 = drawing.get(i).getCenter().y;
            int x2 = drawing.get(i + 1).getCenter().x;
            int y2 = drawing.get(i + 1).getCenter().y;
            g.drawLine(x1, y1, x2, y2);
        }
    }
}
