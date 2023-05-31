package Default;

import java.awt.*;

public class Tooltip {
        private static Tooltip instance = null;
        private Board b;

        private Tooltip(Board b) {
                this.b = b;
        }

        public static Tooltip getInstance(Board b) {
                if (instance == null) {
                        instance = new Tooltip(b);
                }
                return instance;
        }

        static int mouseX;
        static int mouseY;
        static String information = "";
        static boolean show;

        public static void getInfo (String info) {
            information =  info;
            show = true;
        }

        public static void getCoords (int x, int y) {
            mouseX = x;
            mouseY = y;
        }

        public void paint(Graphics g) {
            if (!information.isEmpty()) {
                Font font = new Font("Times New Roman", Font.BOLD, 10);
                FontMetrics metrics = g.getFontMetrics(font);
                int textWidth = metrics.stringWidth(information);
                int textHeight = metrics.getAscent() - metrics.getDescent();

                int boxHeight = 30;
                int boxWidth = Math.max(100,textWidth+10);

                g.setColor(Color.LIGHT_GRAY);
                int x = mouseX + 10;
                int y = mouseY + 10;
                if (mouseX + boxWidth > b.getwidth())
                        x = mouseX - boxWidth - 10;
                if (mouseY + boxHeight > b.getheight())
                        y = mouseY - boxHeight - 10;
                g.fillRect(x, y, boxWidth, boxHeight);
                g.setColor(Color.black);
                g.setFont(font);
                g.drawString(information, x + (boxWidth - textWidth) / 2, y + (textHeight + boxHeight)/2);
            }
        }
}
