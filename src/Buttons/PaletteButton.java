package Buttons;

import Interfaces.Interactibility;

import java.awt.*;

public class PaletteButton extends ActiveButton implements Interactibility {

    public PaletteButton(int x, int y, int size, Color color) {
        super(x, y, size, size);
        this.setRectColor(color);
    }

    public PaletteButton(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.setRectColor(color);
    }

    public void press(int x, int y) {
        if (super.IsClicked(x, y))
            this.setLineColor(new Color(255, 255, 255));
    }
    public void release(int x, int y) {
        this.setLineColor(Color.LIGHT_GRAY);
    }
}
