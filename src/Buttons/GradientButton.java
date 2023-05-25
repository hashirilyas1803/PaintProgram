package Buttons;

import Default.RectangleTemplate;

import java.awt.*;

public class GradientButton extends ToggleButton{
    private RectangleTemplate colorRect;
    private final String text;

    public GradientButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
        this.colorRect = new RectangleTemplate(x + 5, y + 5, width - 10, width - 10, Color.BLACK, Color.LIGHT_GRAY, 2);
        this.text = text;
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
        g.drawString(text, x + 4, y + width + 5);
    }
}
