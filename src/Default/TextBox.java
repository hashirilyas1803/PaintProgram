package Default;

import java.awt.*;

public class TextBox extends RectangleTemplate {
    private int key = 0;
    private String text;
    public TextBox(int x, int y, int width) {
        super(x, y, width, 20, Color.WHITE, Color.LIGHT_GRAY, 2);
        text = "";
    }

    public void input(String text) {
        this.text += text;
    }
    public void delete() {
        this.text = text.substring(0, text.length() - 1);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Write the text
        g.setColor(Color.BLACK);
        Font font = new Font("TimesNewRoman", Font.PLAIN, width / 10);
        g.setFont(font);
        g.drawString(text, centre.x + 5, centre.y + height / 2);
    }
}
