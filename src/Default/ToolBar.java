package Default;

import Buttons.*;
import Buttons.Button;
import Interfaces.DrawButtons;
import Interfaces.Interactibility;
import Interfaces.ToolBarListener;
import Windows.GradientWindow;
import Windows.GradientWindow2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToolBar extends RectangleTemplate implements Interactibility, DrawButtons, ToolBarListener {
    protected Board b;
    protected ArrayList<Button> buttons;
    protected ColorButton selected;
    protected GradientWindow gradient;
    public IncrementerButton strokeSize;
    public static Button[] custom = new Button[5];

    private String type;
    private Image gradientPic= new ImageIcon("src/Resources/gradient.png").getImage();
    public ToolBar(int x, int y, int width, int height, Color rectColor, Color lineColor, int stroke, Board b) {
        super(x, y, width, height, rectColor, lineColor, stroke);
        this.b = b;
        buttons = new ArrayList<>();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!buttons.isEmpty()) {
            for (Button button : buttons) {
                if (button instanceof ColorButton || button instanceof PaletteButton || button instanceof GridButton)
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
        String[] shapes = {"Right-Angled-Triangle", "Equilateral-Triangle", "Rectangle", "Circle", "Hexagon", "Pentagram", "Free-Drawing"};
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if(button.IsClicked(x, y)) {
                if (button instanceof ListButton)
                    b.shape = shapes[i];
                if (button instanceof ToggleButton || button instanceof ListButton) {
                    button.click(x, y);
                    if (button instanceof ColorButton)
                        selected = (ColorButton) button;
                }
            }
            else
                button.Unclick(x, y);
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
        for (Button button : buttons) {
            if (button.IsClicked(x, y)) {
                return true;
            }
        }
        return false;
    }

    public void addShapes(int x, int y, int size) {
        this.type = "Shape";
        Image[] depressed = {new ImageIcon("src/resources/right_angle.png").getImage(), new ImageIcon("src/resources/eq2.png").getImage(), new ImageIcon("src/resources/rectangle.png").getImage(), new ImageIcon("src/resources/circle.png").getImage(), new ImageIcon("src/resources/hexagon.png").getImage(), new ImageIcon("src/resources/pentagram.png").getImage()};
        Image[] pressed = {new ImageIcon("src/resources/right_angle_pressed.png").getImage(), new ImageIcon("src/resources/eq2_pressed.png").getImage(), new ImageIcon("src/resources/rectangle_pressed.png").getImage(), new ImageIcon("src/resources/circle_pressed.png").getImage(), new ImageIcon("src/resources/hexagon_pressed.png").getImage(), new ImageIcon("src/resources/pentagram_pressed.png").getImage()};

        // Iteratively assign a grid of shape buttons
        int j = 3;
        for (int i = 0; i < 6; i++)
            buttons.add(new ListButton(x + ((i % j) * size), y + ((i / j) * size), size, size,depressed[i], pressed[i]));

        // Add a button for free-drawing
        buttons.add(new ListButton(x + (3 * size), y, size * 2, size * 2, new ImageIcon("src/resources/freedraw.png").getImage(), new ImageIcon("src/resources/freedraw_pressed.png").getImage()));
    }

    public void addPaletteButtons(int x, int y, int size, int num) {
        this.type = "Color";
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
        buttons.add(new GradientButton(x + (size * 5), y, size + 22, size * 3, gradientPic, "Gradient"));
        buttons.add(new GridButton(x + (size * 6) + 22 , y, size + 22, (size * 3) / 2, b));
        strokeSize = new IncrementerButton(x + (size * 6) + 22, y + (size * 3) / 2, size + 22, (size * 3) / 2, "Stroke Size", 8,b);
        buttons.add(strokeSize);
        gradient = GradientWindow.getInstance(b.getwidth() / 4, b.getheight() / 5, b.getwidth() / 2, (b.getheight() * 3) / 4);
    }

    @Override
    public void Moved(int x, int y) {
        Tooltip.getCoords(x,y);
        String info = "";
        if (type.equals("Color")) {

            if ( buttons.get(0).IsClicked(x, y) ){
                info = "Stroke Color";
            }
            else if ( buttons.get(1).IsClicked(x, y)  ){
                info = "Fill Color";
            }
            for (int i = 2; i < 15; i++) {
                Button button = buttons.get(i);
                if (button.IsClicked(x, y) ){
                    info = "R: " + button.getRectColor().getRed() + " G: " + button.getRectColor().getGreen() + " B: " + button.getRectColor().getBlue();
                }
            }
            if ( buttons.get(17).IsClicked(x, y) ){
                info = "Gradient";
            }
            else if ( buttons.get(18).IsClicked(x, y)  ){
                info = "Grid size";
            }
            else if ( buttons.get(19).IsClicked(x, y)  ){
                info = "Stroke size";

            }

        }
        else if (type.equals("Shape")) {
            String[] shape = {"Right-Angled-Triangle", "Equilateral-Triangle", "Rectangle", "Circle", "Hexagon", "Pentagram", "Free-Drawing"};
            for (int i = 0; i < buttons.size(); i++) {
                Button button = buttons.get(i);
                if (button.IsClicked(x, y) ){
                    info = shape[i];
                }
            }
        }
        Tooltip.getInfo(info);
    }
}
