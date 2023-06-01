package Windows;

import Default.TextBox;
import Interfaces.Interactibility;

import java.awt.*;

public class SaveWindow extends PopUpWindow implements Interactibility {

    public TextBox textBox;
    public SaveWindow(int x, int y, int width, int height, Color rectColor, Color lineColor, String text) {
        super(x, y, width, height, rectColor, lineColor, text);
        textBox = new TextBox(x + width/4, y + (height/4) +5, width / 2);
    }
    public void write(String text) {
        textBox.input(text);
    }

    public void delete() {
        textBox.delete();
    }

    public String getFilepath(){
        return textBox.getFilepath();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        textBox.paint(g);
    }

    @Override
    public void click(int x, int y) {
        textBox.click(x, y);
    }

    @Override
    public boolean IsClicked(int x, int y) {
        return false;
    }

    @Override
    public void press(int x, int y) {

    }

    @Override
    public void release(int x, int y) {

    }
}
