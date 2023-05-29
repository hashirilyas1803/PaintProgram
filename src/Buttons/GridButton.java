package Buttons;

import Default.Board;

import java.awt.*;

public class GridButton extends IncrementerButton {
    public GridButton(int x, int y, int width, int height, Board b) {
        super(x, y, width, height, "Grid Size", 64,b);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

//        g.setColor(Color.WHITE);
//        g.fillRect(x,y,image_depressed.getWidth(null), image_depressed.getHeight(null));
//
//
//
//        g.setColor(Color.BLACK);
//        g.drawRect(x,y,image_depressed.getWidth(null), image_depressed.getHeight(null));

//        if (size == 0)
//            g.drawString("0",x+5,y+40);
//        else



        if (size > 0) {
            g.setColor(new Color(92, 98, 116));

            int xStart = b.panel.getCentre().x;
            int xEnd = b.panel.getWidth();
            int yStart = b.panel.getCentre().y;
            int yEnd =  b.panel.getCentre().y + b.panel.getHeight();


            int y = yStart;
            while (y <= b.panel.getCentre().y + b.panel.getHeight()) {
                g.drawLine(xStart, y, xEnd, y);
                y += size;
            }

            int x = xStart;
            while (x <= (b.panel.getWidth())) {
                g.drawLine(x, yStart, x, yEnd);
                x += size;
            }
        }
    }
}
