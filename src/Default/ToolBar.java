package Default;

import Buttons.*;
import Buttons.Button;
import Interfaces.DrawButtons;
import Interfaces.Interactibility;
import Windows.GradientWindow;
import Windows.GradientWindow2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToolBar extends RectangleTemplate implements Interactibility, DrawButtons {
    protected Board b;
    protected ArrayList<Button> buttons;
    protected ColorButton selected;
    protected GradientWindow gradient;
    private Button [] array3;
    private GradientButton gradientButton;
    public static Button[] custom = new Button[5];
    private Image gradientPic= new ImageIcon("src/Resources/gradient.png").getImage();
    public ToolBar(int x, int y, int width, int height, Color rectColor, Color lineColor, int stroke, Board b) {
        super(x, y, width, height, rectColor, lineColor, stroke);
        this.b = b;
        buttons = new ArrayList<>();
        int h = b.getheight();
        int w = b.getwidth();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!buttons.isEmpty()) {
            for (Button button : buttons) {
                if (button instanceof ColorButton || button instanceof PaletteButton)
                    button.paint(g);
                else
                    drawButton(button, g, b);
            }
        }
        if (gradient != null)
            gradient.paint(g);

    }

    public static void addColor (Color color, int buttonNumber)
    {
        switch (buttonNumber) {
            case 1 -> custom[0].setRectColor(color);
            case 2 -> custom[1].setRectColor(color);
            case 3 -> custom[2].setRectColor(color);
            case 4 -> custom[3].setRectColor(color);
            case 5 -> custom[4].setRectColor(color);

        }
    }

    public Color getStrokeColor() {
        if (buttons.get(0) instanceof ColorButton) {
            return ((ColorButton) buttons.get(0)).getColorRect();
        }
        return null;
    }

    public Color getFillColor() {
        if (buttons.get(1) instanceof ColorButton) {
            return ((ColorButton) buttons.get(1)).getColorRect();
        }
        return null;
    }


    protected void drawButton(Button button, Graphics g, Board b) {
        g.drawImage(button.GetImage(), button.x, button.y, b);
    }

    @Override
    public void click(int x, int y) {
        for (int i = 0; i < buttons.size(); i++) {
            if(buttons.get(i).IsClicked(x, y)) {
                if (buttons.get(i) instanceof ToggleButton || buttons.get(i) instanceof ListButton) {
                    buttons.get(i).click(x, y);
                    if (buttons.get(i) instanceof ColorButton)
                        selected = (ColorButton) buttons.get(i);
                }
            }
            else
                buttons.get(i).Unclick(x, y);
        }
    }

    public void press(int x, int y) {
        for (Button button : buttons) {
            if (button instanceof PaletteButton && button.IsClicked(x, y)) {
                button.press(x, y);
                if (selected != null)
                    selected.setColorRect(button.getRectColor());
            }
        }
    }
    public void release(int x, int y) {
        for (Button button : buttons) {
            if (button instanceof PaletteButton && button.IsClicked(x, y)) {
                button.release(x, y);
            }
        }
    }

    @Override
    public boolean IsClicked(int x, int y) {
        if (buttons.get(0) instanceof ListButton) {
            String[] shapes = {"Right-Angled-Triangle", "Equilateral-Triangle", "Rectangle", "Circle", "Hexagon", "Pentagram"};
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).IsClicked(x, y)) {
                    b.shape = shapes[i];
                    return true;
                }
            }
            return false;
        }
        else {
            for (Button button : buttons) {
                if (button.IsClicked(x, y)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void addShapes(int x, int y, int size) {
        buttons.add(new ListButton(x, y, size, size, new ImageIcon("src/resources/right_triangle_button.png").getImage(), new ImageIcon("src/resources/right_triangle_button_pressed.png").getImage()));
        buttons.add(new ListButton(x + b.getHEIGHT(), y, size, size, new ImageIcon("src/resources/equilateral_triangle_button.png").getImage(), new ImageIcon("src/resources/equilateral_triangle_button_pressed.png").getImage()));
        buttons.add(new ListButton(x + (b.getHEIGHT() * 2), y, size, size, new ImageIcon("src/resources/rectangle_button.png").getImage(), new ImageIcon("src/resources/rectangle_button_pressed.png").getImage()));
        buttons.add(new ListButton(x, y + b.getHEIGHT(), size, size, new ImageIcon("src/resources/circle_button.png").getImage(), new ImageIcon("src/resources/circle_button_pressed.png").getImage()));
        buttons.add(new ListButton(x + b.getHEIGHT(), y + b.getHEIGHT(), size, size, new ImageIcon("src/resources/hexagon_button.png").getImage(), new ImageIcon("src/resources/hexagon_button_pressed.png").getImage()));
        buttons.add(new ListButton(x + (b.getHEIGHT() * 2), y + b.getHEIGHT(), size, size, new ImageIcon("src/resources/pentagram_button.png").getImage(), new ImageIcon("src/resources/pentagram_button_pressed.png").getImage()));
    }

    public void addPaletteButtons(int x, int y, int size, int num) {
        // Populate array of colors to iteratively assign to palette buttons
        Color[] colors = {Color.BLACK, Color.gray, Color.RED, Color.ORANGE, Color.PINK, Color.WHITE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE};

        // Iterative assign a grid of colors
        int j = num / 3;
        for (int i = 0; i < num; i++) {
            buttons.add(new PaletteButton(x + ((i % j) * size), y + ((i / j) * size), size, colors[i]));
        }
        for (int i = 0; i < custom.length; i++) {
            custom[i] = buttons.get(i + 12);
        }


        gradientButton = new GradientButton(x + (size * 5), y , size + 22, size * 3, gradientPic, "Gradient");
//        grid = new Grid(gradient.x + gradient.width + 10 , gradient.y, pressed.getWidth(null), pressed.getHeight(null), depressed, pressed, toolbar_color);
//        strokeSize = new StrokeSize(grid.x + grid.width + 10, grid.y, strokePic.getWidth(null),strokePic.getHeight(null), strokePic, strokePic, toolbar_color);
//        array3 = new Button[]{ gradientButton};
        buttons.add(gradientButton);
        gradient = GradientWindow.getInstance(b.getwidth() / 4, b.getheight() / 4, b.getwidth() / 2, b.getwidth() / 2);
    }
}
