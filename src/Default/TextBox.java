package Default;

import Interfaces.Interactibility;
import Windows.Window;

import java.awt.*;
import java.io.Serializable;

public class TextBox extends Window implements Interactibility{
    private int key = 0;
    boolean selected;
     String text;
    public TextBox(int x, int y, int width) {
        super(x, y, width, 20, Color.WHITE, Color.LIGHT_GRAY, 2);
        text = "";
    }

    public void input(String text) {
        if (selected)
            this.text += text;
    }
    public void delete() {
        if (selected && text.length() > 0)
            this.text = text.substring(0, text.length() - 1);
    }

    @Override
    public void paint(Graphics g) {
        if (selected)
            this.setLineColor(new Color(200, 200, 255));
        else
            this.setLineColor(Color.LIGHT_GRAY);
        super.paint(g);
        // Write the text
        g.setColor(Color.BLACK);
        Font font = new Font("TimesNewRoman", Font.PLAIN, width / 10);
        g.setFont(font);
        g.drawString(text, centre.x + 5, centre.y + height / 2);
    }

    @Override
    public void click(int x, int y) {
        selected = inBounds(x, y);
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

    public String getFilepath() {
        return text;
    }
}
