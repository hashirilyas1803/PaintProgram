package Windows;

import Default.RectangleTemplate;
import Default.ToolBar;

import java.awt.*;
import java.util.ArrayList;

public class Window extends RectangleTemplate {
    private ArrayList<ToolBar> toolBars;
    public Window(int x, int y, int width, int height, Color rectColor, Color lineColor, int stroke) {
        super(x, y, width, height, rectColor, lineColor, stroke);
        toolBars = new ArrayList<>();
    }

    public void addToolBar(ToolBar toolBar) {
        toolBars.add(toolBar);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!toolBars.isEmpty()) {
            for (ToolBar toolBar : toolBars) {
                toolBar.paint(g);
            }
        }
    }



    public boolean inBounds(int x, int y) {
        return (x > centre.x && x < centre.x + width && y > centre.y && y < centre.y + height);
    }


}
