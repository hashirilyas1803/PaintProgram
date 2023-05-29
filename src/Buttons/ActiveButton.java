package Buttons;

import Interfaces.Interactibility;

import java.awt.*;

public class ActiveButton extends Button implements Interactibility {
    private String text;


    public ActiveButton(int x, int y, int width, int height, Image i_depressed, Image i_pressed) {
        super(x, y, width, height, i_depressed, i_pressed);
    }

    public ActiveButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height);
        this.text = text;
    }

    public ActiveButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void press(int x, int y) {
        super.press(x, y);
        if (IsClicked(x, y))
            setLineColor(Color.WHITE);
    }

    @Override
    public void release(int x, int y) {
        super.release(x, y);
        if (IsClicked(x, y))
            this.setLineColor(Color.LIGHT_GRAY);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (text != null){
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesNewRoman", Font.BOLD, 10));
            g.drawString(text, x +  width / 4 - width / 10, y + height / 2);
        }
    }
}
