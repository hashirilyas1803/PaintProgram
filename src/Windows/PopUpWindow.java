package Windows;

import Default.RectangleTemplate;

import java.awt.*;

public class PopUpWindow extends Window{
    private String text;
    RectangleTemplate rect;
    public PopUpWindow(int x, int y, int width, int height, Color rectColor, Color lineColor, String text) {
        super(x, y, width, height, rectColor, lineColor, 2);
        this.text = text;
        this.rect = new RectangleTemplate(x, y, width, height / 20, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0);
    }
    public PopUpWindow(int x, int y, int width, int height, String text) {
        super(x, y, width, height, new Color(250, 250, 250), Color.LIGHT_GRAY, 2);
        this.text = text;
        this.rect = new RectangleTemplate(x, y, width, height / 20, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        rect.paint(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesNewRoman", Font.BOLD, height / (20 + text.length())));
        g.drawString(text, getCentre().x, getCentre().y + 15);
    }
}
