package Buttons;

import java.awt.*;

public class GradientButton extends ColorButton {
    public static boolean window;
    public GradientButton(int x, int y, int width, int height, Image image, String text) {
        super(x, y, width, height, image, text);
    }


    public void click(int x, int y) {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
            window = true;
    }
}

