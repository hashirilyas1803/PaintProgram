package Default;

import Buttons.MenuButton;
import Buttons.Button;
import Interfaces.DrawButtons;
import Interfaces.Interactibility;
import Windows.PopUpWindow;
import Canvas.*;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Header extends RectangleTemplate implements Interactibility, DrawButtons {
    // Attributes
    Board b;
    MenuButton file;
    MenuButton edit;
    DropDownMenu fileMenu;
    DropDownMenu editMenu;
    PopUpWindow open;
    PopUpWindow save;
    TextBox textBox;
    boolean fileop, editop;
    int count = 1;
    Serialization serialization = new Serialization();

    // Methods
    public Header(int x, int y, int width, int height, Color rectColor, Color lineColor, int stroke, Board b) {
        super(x, y, width, height, rectColor, lineColor, stroke);
        this. b = b;
        file = new MenuButton(0, 0, 64, height, new ImageIcon("src/resources/file_button.png").getImage(), new ImageIcon("src/resources/file_button_pressed.png").getImage());
        edit = new MenuButton(64, 0, 64, height, new ImageIcon("src/resources/edit_button.png").getImage(), new ImageIcon("src/resources/edit_button_pressed.png").getImage());
        fileMenu = new DropDownMenu(file.x, file.y + height, 128, height * 3, Color.GRAY, Color.BLACK, 0);
        editMenu = new DropDownMenu(edit.x, edit.y + height, width / 8, height * 2, Color.GRAY, Color.BLACK, 0);
        int h = b.getheight();
        open = new PopUpWindow(width / 4, h / 4, width / 2, h / 2, Color.WHITE, Color.LIGHT_GRAY, "Open");
        save = new PopUpWindow(width / 4, h / 4, width / 2, h / 2, Color.WHITE, Color.LIGHT_GRAY, "Save");
        textBox = new TextBox(width/4, (h/4) +5, 20);
        setUp();
    }

    private void setUp() {
        // Set up the dropdown menu for the file button
        fileMenu.addButton(file.x, file.y + height, 128, height, new ImageIcon("src/resources/new_button.png").getImage(), new ImageIcon("src/resources/new_button_pressed.png").getImage());
        fileMenu.addButton(file.x, file.y + (2 * height), 128, height, new ImageIcon("src/resources/open_button.png").getImage(), new ImageIcon("src/resources/open_button.png").getImage());
        fileMenu.addButton(file.x, file.y + (3 * height), 128, height, new ImageIcon("src/resources/save_button.png").getImage(), new ImageIcon("src/resources/save_button.png").getImage());

        // Set up the dropdown menu for the edit button
        editMenu.addButton(edit.x, edit.y + height, 128, height, new ImageIcon("src/resources/undo_button.png").getImage(), new ImageIcon("src/resources/undo_button.png").getImage());
        editMenu.addButton(edit.x, edit.y + (2 * height), 128, height, new ImageIcon("src/resources/redo_button.png").getImage(), new ImageIcon("src/resources/redo_button.png").getImage());
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
            fileop = true;
            for (Button button : fileMenu.getButtons()) {
                drawButton(button, g, b);
            }
        }
        if (edit.IsPressed()) {
            editMenu.paint(g);
            editop = true;
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
        file.click(x, y);
        edit.click(x, y);

        // Add functionality to each of the buttons in the dropdown menus
        int i = 0;
        for (Button button : fileMenu.getButtons()) {
            if (button.IsClicked(x,y) && fileop) {
                switch (i) {
                    case 0:
                        System.out.println("New");
                        b.layer = new LinkedList<>();
                        b.InitializeAssets();
                        break;
                    case 1:
                        serialization.setFilepath("hashir");
                        serialization.reading(b);
                        break;
                    case 2:
//                       if (count == 1){
//                           textBox.paint(g);
//                           serialization.setFilepath("hashir");
//                       }
                        serialization.setFilepath("hashir");
                        serialization.writing(b);
                        //count++;
                        break;
                }
            }
            i++;
        }
        i = 0;
        for (Button button : editMenu.getButtons()) {
            if (button.IsClicked(x,y) && editop) {
                switch (i) {
                    case 0:
                        b.undo();
                        System.out.println("Undo");
                        break;
                    case 1:
                        int temp = b.layer.size() - 1;
                        if (!b.redo.get(temp).isEmpty())
                            b.layer.get(temp).push(b.redo.get(temp).dequeue());
                        System.out.println("Redo");
                        break;
                }
            }
            i++;
        }

        for (Button button : fileMenu.getButtons()) {
            button.click(x, y);
        }
        for (Button button : editMenu.getButtons()) {
            button.click(x, y);
        }
    }

    public boolean IsClicked(int x, int y) {
        if (file.IsClicked(x, y) || edit.IsClicked(x, y))
            return true;
        return false;
    }

    @Override
    public void press(int x, int y) {

    }

    @Override
    public void release(int x, int y) {

    }
}
