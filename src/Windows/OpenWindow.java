package Windows;

import Default.TextBox;

import java.awt.*;

public class OpenWindow extends PopUpWindow{
    TextBox textBox;
    public OpenWindow(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
        textBox = new TextBox(width/4, (height/4) +5, 20);

    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);


    }

}
