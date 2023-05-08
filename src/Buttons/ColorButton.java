package Buttons;

import Default.Rectangle;
import Interfaces.Interactibility;

import java.awt.*;

public class ColorButton extends ToggleButton implements Interactibility {
    private Rectangle colorRect;
    private final String text;
    public ColorButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height);
        this.colorRect = new Rectangle(x + 5, y + 5, width - 10, width - 10, Color.BLACK, Color.LIGHT_GRAY, 2);
        this.text = text;
    }


    public void setColorRect(Color colorRect) {
        this.colorRect.setRectColor(colorRect);
    }

    @Override
    public void click(int x, int y) {
        super.click(x, y);
        this.setLineColor(new Color(225, 225, 255));
        this.setRectColor(new Color(200, 200, 255));
    }

    @Override
    public void Unclick(int x, int y) {
        this.setLineColor(Color.LIGHT_GRAY);
        this.setRectColor(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        colorRect.paint(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesNewRoman", Font.PLAIN, 10));
        String[] text1 = text.split(" ");
        g.drawString(text1[0], x + 5, y + width + 5);
        g.drawString(text1[1], x + 5, y + width + 15);
    }
}
