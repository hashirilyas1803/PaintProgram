package Default;

import javax.swing.*;
import java.awt.*;

/*public class ShapesToolbar extends ToolBar{

    Color color;
    private Button button;
    static Button selectedButton;
    static String selectedShapeName;
    public ShapesToolbar(int x, int y, int width, int height, int num, ImageIcon[] array2, Color color) {
        super(x, y, width, height, num, array2, color);
        super.load_array(array2);
        this.color = color;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        for (int i = 0; i < 7; i++) {
            g.drawRect(array1[i].x - 5, array1[i].y - 5  , array2[i].getIconWidth() + 10, array2[i].getIconHeight() + 10);
        }

    }
    public static String getSelectedShapeName()
    {
        if (selectedButton != null && selectedButton.pressed && selectedShapeName != null)
            return selectedShapeName;
        else
            return "";
    }


    @Override
    public void click(int x, int y) {
        for (int i = 0; i < 7; i++) {
            array1[i].IsClicked(x,y);
        }
        for (Button button : array1) {
            button.IsClicked(x, y);
            if (button.pressed) {
                selectedButton = button;
                if (button instanceof ShapeButton)
                    selectedShapeName = ((ShapeButton) button).shapeName;

                //deselect all others
                for (Button button1 : array1) {
                    if (button1 != selectedButton) {
                        button1.pressed = false;
                        button1.current_image = button1.image_depressed;
                    }
                }
            }
        }


    }

    @Override
    public void Pressed(int x, int y) {

    }

    @Override
    public void Released(int x, int y) {

    }

    @Override
    public void Moved(int x, int y) {
        Tooltip.getCoords(x,y);
        String[] shapes = {"Right Angled Triangle", "Equilateral Triangle","Rectangle", "Circle","Pentagram","Hexagon", "Free Draw"};
        String info = "";
        for (int i = 0; i < 7; i++) {
            if ( x > array1[i].x && x < array1[i].x + array1[i].width && y >array1[i].y && y < array1[i].y +array1[i].height ){
                    info = shapes[i];
            }
        }
        Tooltip.getInfo(info);

    }
}*/