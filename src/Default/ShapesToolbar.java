//package Default;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class ShapesToolbar extends ToolBar{
//
//    Color color;
//    private Button button;
//    static Button selectedButton;
//    static String selectedShapeName;
//    ImageIcon edit = new ImageIcon("src/Images/myimage_edit.png");
//    ImageIcon file = new ImageIcon("src/Images/myimage_file.png");
//    ImageIcon right = new ImageIcon("src/Images/right angle.png");
//    ImageIcon equilateral = new ImageIcon("src/Images/eq2.png");
//    ImageIcon rectangle = new ImageIcon("src/Images/rect2.png");
//    ImageIcon circle = new ImageIcon("src/Images/circle.png");
//    ImageIcon pentagram = new ImageIcon("src/Images/pentagram.png");
//    ImageIcon hexagon = new ImageIcon("src/Images/hexagon.png");
//    ImageIcon free = new ImageIcon("src/Images/freedraw.png");
//    ImageIcon [] array3 = {right, equilateral, rectangle, circle, pentagram, hexagon, free};
//    public ShapesToolbar(int x, int y, int width, int height, Board b) {
//        super(x, y, width, height, Color.WHITE, Color.LIGHT_GRAY, 2, b);
//    }
//
//    @Override
//    public void paint(Graphics g){
//        super.paint(g);
//        for (int i = 0; i < 7; i++) {
//            g.drawRect(array1[i].x - 5, array1[i].y - 5  , array2[i].getIconWidth() + 10, array2[i].getIconHeight() + 10);
//        }
//
//    }
//    public static String getSelectedShapeName()
//    {
//        if (selectedButton != null && selectedButton.pressed && selectedShapeName != null)
//            return selectedShapeName;
//        else
//            return "";
//    }
//
//
//    @Override
//    public void click(int x, int y) {
//        for (int i = 0; i < 7; i++) {
//            array1[i].IsClicked(x,y);
//        }
//        for (Button button : array1) {
//            button.IsClicked(x, y);
//            if (button.pressed) {
//                selectedButton = button;
//                if (button instanceof ShapeButton)
//                    selectedShapeName = ((ShapeButton) button).shapeName;
//
//                //deselect all others
//                for (Button button1 : array1) {
//                    if (button1 != selectedButton) {
//                        button1.pressed = false;
//                        button1.current_image = button1.image_depressed;
//                    }
//                }
//            }
//        }
//
//
//    }
//
//    @Override
//    public void Pressed(int x, int y) {
//
//    }
//
//    @Override
//    public void Released(int x, int y) {
//
//    }
//
//    @Override
//    public void Moved(int x, int y) {
//        Tooltip.getCoords(x,y);
//        String[] shapes = {"Right Angled Triangle", "Equilateral Triangle","Rectangle", "Circle","Pentagram","Hexagon", "Free Draw"};
//        String info = "";
//        for (int i = 0; i < 7; i++) {
//            if ( x > array1[i].x && x < array1[i].x + array1[i].width && y >array1[i].y && y < array1[i].y +array1[i].height ){
//                    info = shapes[i];
//            }
//        }
//        Tooltip.getInfo(info);
//
//    }
//}