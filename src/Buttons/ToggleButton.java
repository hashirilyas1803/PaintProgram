package Buttons;

import Interfaces.Interactibility;

import java.awt.*;

public class ToggleButton extends Button implements Interactibility {
    // Attributes
    int count;


    // Methods
    public ToggleButton(int x, int y, int width, int height, Image i_depressed, Image i_pressed) {
        super(x, y, width, height, i_depressed, i_pressed);
    }

    public ToggleButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public ToggleButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height);
    }

    @Override
    public void click(int x, int y) {
        if (count == 0) {
            count++;
            super.click(x, y);
            return;
        }
        count = 0;
        super.IsReleased(x,y);
    }
}
