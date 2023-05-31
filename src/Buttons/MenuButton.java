package Buttons;

import Interfaces.Interactibility;

import java.awt.*;

public class MenuButton extends Button implements Interactibility {

    // Constructor
    public MenuButton(int x, int y, int width, int height, Image i_depressed, Image i_pressed) {
        super(x, y, width, height, i_depressed, i_pressed);
    }

    @Override
    public void click(int x, int y) {
        if (!super.IsClicked(x, y) || super.IsPressed()) {
            super.setCurrent_image(image_depressed);
            super.SetPressed(false);
        }
        super.click(x, y);
    }
}
