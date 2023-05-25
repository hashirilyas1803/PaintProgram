package Windows;

import Default.RectangleTemplate;
import Interfaces.Interactibility;

import java.awt.*;

public class GradientWindow extends PopUpWindow implements Interactibility {

    int xtemp;
    int ytemp;
    private RectangleTemplate[][] rectangles;
    public GradientWindow(int x, int y, int width, int height, Color rectColor, Color lineColor, String text) {
        super(x, y, width, height, rectColor, lineColor, text);
        xtemp = x + width / 10;
        ytemp = y + height / 10;

        // Initialize the rectangles array
        rectangles = new RectangleTemplate[255][301];
        int r, g, b;
        int r1 = 255, g1 = 0, b1 = 0;

        // Assign color vertically first
        for (int j = 0; j < rectangles[0].length; j++) {
            // Calculate the color values
            if (r1 == 255 && g1 < 255 && b1 == 0)
                 g1 += 5;
            else if (g1 == 255 && b1 < 255) {
                if (r1 >= 5)
                    r1 -= 5;
                else if (r1 == 0)
                    b1 += 5;
            }
            else if (b1 == 255 && r1 < 255) {
                if (g1 >= 5)
                    g1 -= 5;
                else
                    r1 += 5;
            }
            else if (b1 >= 5) {
                b1 -= 5;
            }
            r = r1;
            g = g1;
            b = b1;

            // Assign color values horizontally
            for (int i = 0; i < rectangles.length; i++) {
                rectangles[i][j] = new RectangleTemplate(xtemp + j, ytemp + i, 1, 1, new Color(r,g,b), null, 0);
                if(r != 255)
                    r += (255 - r) / (rectangles.length - i);
                if(g != 255)
                    g += (255 - g) / (rectangles.length - i);
                if(b != 255)
                    b += (255 - b) / (rectangles.length - i);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[0].length; j++) {
                rectangles[i][j].paint(g);
            }
        }
    }

    @Override
    public void click(int x, int y) {
        if(IsClicked(x, y)) {
//            System.out.println(rectangles[y - ytemp][x - xtemp].getRectColor());
        }
    }

    @Override
    public boolean IsClicked(int x, int y) {
        int xt = rectangles[0][0].getCentre().x;
        int yt = rectangles[0][0].getCentre().y;
        int w = rectangles[0].length;
        int h = rectangles.length;
        if (x > xt && x < xt + w && y > yt && y < yt + h)
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
