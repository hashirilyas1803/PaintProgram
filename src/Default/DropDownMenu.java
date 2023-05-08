package Default;

import Buttons.Button;
import Buttons.MenuButton;

import java.awt.*;
import java.util.ArrayList;

public class DropDownMenu extends Rectangle{
    // Attributes
    private ArrayList<Button> buttons = new ArrayList<>();

    // METHODS
    // Constructor
    public DropDownMenu(int x, int y, int width, int height, Color rectColor, Color lineColor, int stroke) {
        super(x, y, width, height, rectColor, lineColor, stroke);
    }

    // Add new buttons to the dropdown menu
    public void addButton(int x, int y, int width, int height, Image i_depressed, Image i_pressed) {
        buttons.add(new MenuButton(x, y, width, height, i_depressed, i_pressed));
    }

    // Remove buttons from the dropdown menu
    public void removeButton(int location) {
        buttons.remove(location);
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }
}
