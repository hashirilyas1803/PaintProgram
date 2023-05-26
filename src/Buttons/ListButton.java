package Buttons;

import java.awt.*;

public class ListButton extends Button{
    private String text;

    public ListButton(int x, int y, int width, int height, Image i_depressed, Image i_pressed) {
        super(x, y, width, height, i_depressed, i_pressed);
    }



    public ListButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height);
        this.text = text;
    }

    @Override
    public boolean IsClicked(int x, int y) {
        return super.IsClicked(x, y);
    }

    @Override
    public void press(int x, int y) {
        super.press(x, y);
    }

    @Override
    public void click(int x, int y) {
        if (IsClicked(x, y)) {
            if (image_depressed == null || image_pressed == null)
                showSelection();
        }
    }

    public void showSelection() {
        setLineColor(Color.BLACK);
    }

    @Override
    public void release(int x, int y) {
        super.release(x, y);
    }

    @Override
    public void Unclick(int x, int y) {
        super.Unclick(x, y);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (text != null) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesNewRoman", Font.PLAIN, 10));
            g.drawString(text, x + 5, y + height / 2);
        }
    }
}
