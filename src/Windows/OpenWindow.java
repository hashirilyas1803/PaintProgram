package Windows;

import Buttons.ListButton;
import Default.TextBox;
import Interfaces.Interactibility;

import java.awt.*;
import java.io.File;

public class OpenWindow extends PopUpWindow implements Interactibility {


    ListButton [] buttons;
    public String filepath;

    public OpenWindow(int x, int y, int width, int height, Color rectColor, Color lineColor, String text) {
        super(x, y, width, height, rectColor, lineColor, text);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (buttons != null) {
            for (ListButton button : buttons) {
                button.paint(g);
            }
        }

    }



    public void files(File[] list){
        buttons = new ListButton[list.length];
        for (int i = 0; i < list.length; i++) {
            buttons[i] = (new ListButton(super.getCentre().x + stroke , super.getCentre().y + height - 32*(i+1) - stroke, super.width - (stroke * 2), 32, list[i].getName()));
        }

    }


    @Override
    public void click(int x, int y) {
        if (buttons != null){
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].click(x,y);
                filepath = buttons[i].getText();
            }
//            for (int i = 0; i < buttons.length; i++) {
//                if (buttons[i].IsClicked(x,y)){
//                    filepath = buttons[i].getText();
//                }
//            }
        }
    }

    @Override
    public boolean IsClicked(int x, int y) {
        return inBounds(x, y);
    }

    @Override
    public void press(int x, int y) {

    }

    @Override
    public void release(int x, int y) {

    }
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
