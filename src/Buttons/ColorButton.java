package Buttons;

import Default.RectangleTemplate;
import Interfaces.Interactibility;

import java.awt.*;

public class ColorButton extends ToggleButton implements Interactibility {
    private boolean empty;
    private RectangleTemplate colorRect;
    private final String text;
    private Image image;
    public ColorButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height);
        this.colorRect = new RectangleTemplate(x + width / 10, y + height / 7, (width * 3) / 4, (width * 3) / 4, Color.BLACK, Color.LIGHT_GRAY, 2);
        this.text = text;
    }

    public ColorButton(int x, int y, int width, int height, Image image, String text) {
        super(x, y, width, height);
        this.image = image.getScaledInstance((width * 3) / 4, (width * 3) / 4, Image.SCALE_FAST);
        this.text = text;
    }
    public ColorButton(int x, int y, int width, int height, boolean empty, String text) {
        super(x, y, width, height);
        this.colorRect = new RectangleTemplate(x + width / 10, y + height / 7, (width * 3) / 4, (width * 3) / 4, Color.BLACK, Color.LIGHT_GRAY, 2);
        this.text = text;
        this.empty = empty;
    }


    public void setColorRect(Color colorRect) {
        this.colorRect.setRectColor(colorRect);
    }

    public Color getColorRect() {
        return this.colorRect.getRectColor();
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
        if (!empty) {
            if (image == null)
                colorRect.paint(g);
            else
                g.drawImage(image, x + width / 10, y + height / 7, null);
        }

        g.setColor(Color.BLACK);
        int size = 14;
        g.setFont(new Font("TimesNewRoman", Font.PLAIN, size));
        String[] text1 = text.split(" ");
        g.drawString(text1[0], x + width / 10, y + (height * 3) / 5 + 5);
        if (text1.length == 2)
            g.drawString(text1[1], x + width / 10, y + (height * 3) / 5 + 5 + size);
    }
}
