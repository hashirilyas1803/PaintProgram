package Default;

import Buttons.MenuButton;
import Buttons.Button;
import Interfaces.DrawButtons;
import Interfaces.Interactibility;
import Windows.PopUpWindow;

import javax.swing.*;
import java.awt.*;

public class Header extends RectangleTemplate implements Interactibility, DrawButtons {
    // Attributes
    Board b;
    MenuButton file;
    MenuButton edit;
    DropDownMenu fileMenu;
    DropDownMenu editMenu;
    PopUpWindow open;

    // Methods
    public Header(int x, int y, int width, int height, Color rectColor, Color lineColor, int stroke, Board b) {
        super(x, y, width, height, rectColor, lineColor, stroke);
        this. b = b;
        file = new MenuButton(0, 0, 64, height, new ImageIcon("src/resources/file_button.png").getImage(), new ImageIcon("src/resources/file_button_pressed.png").getImage());
        edit = new MenuButton(64, 0, 64, height, new ImageIcon("src/resources/edit_button.png").getImage(), new ImageIcon("src/resources/edit_button_pressed.png").getImage());
        fileMenu = new DropDownMenu(file.x, file.y + height, 64, height * 3, Color.GRAY, Color.BLACK, 0);
        editMenu = new DropDownMenu(edit.x, edit.y + height, 64, height * 2, Color.GRAY, Color.BLACK, 0);
        int h = b.getheight();
        open = new PopUpWindow(width / 4, h / 4, width / 2, h / 2, Color.WHITE, Color.LIGHT_GRAY, "Open");
        setUp();
    }

    private void setUp() {
        // Set up the dropdown menu for the file button
        fileMenu.addButton(file.x, file.y + height, 64, height, new ImageIcon("src/resources/new_button.png").getImage(), new ImageIcon("src/resources/new_button_pressed.png").getImage());
        fileMenu.addButton(file.x, file.y + (2 * height), 64, height, new ImageIcon("src/resources/open_button.png").getImage(), new ImageIcon("src/resources/open_button.png").getImage());
        fileMenu.addButton(file.x, file.y + (3 * height), 64, height, new ImageIcon("src/resources/save_button.png").getImage(), new ImageIcon("src/resources/save_button.png").getImage());

        // Set up the dropdown menu for the edit button
        editMenu.addButton(edit.x, edit.y + height, 64, height, new ImageIcon("src/resources/undo_button.png").getImage(), new ImageIcon("src/resources/undo_button.png").getImage());
        editMenu.addButton(edit.x, edit.y + (2 * height), 64, height, new ImageIcon("src/resources/redo_button.png").getImage(), new ImageIcon("src/resources/redo_button.png").getImage());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Display the file and edit buttons
        drawButton(file, g, b);
        drawButton(edit, g, b);

        // Display the dropdown menus based on which button is pressed
        if (file.IsPressed()) {
            fileMenu.paint(g);
            for (Button button : fileMenu.getButtons()) {
                drawButton(button, g, b);
            }
        }
        if (edit.IsPressed()) {
            editMenu.paint(g);
            for (Button button : editMenu.getButtons()) {
                drawButton(button, g, b);
            }
        }
    }

    private void drawButton(Button button, Graphics g, Board b) {
        g.drawImage(button.GetImage(), button.x, button.y, b);
    }

    @Override
    public void click(int x, int y) {
        // Add clicking functionality to all buttons in the header
        int i = 0;
        for (Button button : editMenu.getButtons()) {
            if (button.IsClicked(x, y) && edit.IsPressed()) {
                //TODO Saad Add the file buttons' functionality here
            }
        }
            for (Button button : editMenu.getButtons()) {
            if (button.IsClicked(x, y) && edit.IsPressed()) {
                switch (i) {
                    case 0:
                        b.undo();
                        System.out.println("Undo");
                        break;
                    case 1:
                        if (!b.redo.isEmpty())
                            b.layer.get(0).push(b.redo.dequeue());
                        System.out.println("Redo");
                        break;
                }
            }
            i++;
        }
        file.click(x, y);
        edit.click(x, y);
    }

    public boolean IsClicked(int x, int y) {
        if (file.IsClicked(x, y) || edit.IsClicked(x, y))
            return true;
        for (Button button : fileMenu.getButtons()) {
            if (button.IsClicked(x, y) && file.IsPressed())
                return true;
        }
        for (Button button : editMenu.getButtons()) {
            if (button.IsClicked(x, y) && edit.IsPressed())
                return true;
        }
        return false;
    }

    @Override
    public void press(int x, int y) {

    }

    @Override
    public void release(int x, int y) {

    }
}
