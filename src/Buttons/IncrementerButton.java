package Buttons;

import Default.Board;

import java.awt.*;

public class IncrementerButton extends ColorButton{
    protected Board b;
    protected int size;
    protected int limit;

    public IncrementerButton(int x, int y, int width, int height, String text, int limit, Board b) {
        super(x, y, width, height, true, text);
        this.limit = limit;
        this.b  = b;
    }

    public void click(int x, int y) {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
        {
            if (size == 0)
                size += 2;
            else if (size == limit)
                size = 0;
            else
                size *= 2;
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Font font = new Font("Arial",Font.PLAIN,30);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics();
        int stringWidth = metrics.stringWidth(String.valueOf(size));
        int stringHeight = metrics.getHeight();
        int centerX = x + 30 - stringWidth / 2;
        int centerY = y + 10 + stringHeight / 2;
        g.drawString(String.valueOf(size),centerX,centerY);
    }
}
