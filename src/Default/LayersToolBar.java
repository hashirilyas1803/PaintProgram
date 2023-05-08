package Default;

import Buttons.ActiveButton;
import Buttons.ListButton;
import Interfaces.DrawButtons;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.swap;

public class LayersToolBar extends ToolBar implements DrawButtons {
    ActiveButton[] activeButtons;
    private LinkedList<ListButton> buttons;
    public ListButton selected;
    public LayersToolBar(int x, int y, int width, int height, Color rectColor, Color lineColor, int stroke, Board b) {
        super(x, y, width, height, rectColor, lineColor, stroke, b);
        buttons = new LinkedList<>();

        // Add the adding removing buttons
        activeButtons = new ActiveButton[4];
        int bx = x + stroke;
        int by = y + stroke;
        int bw = width / activeButtons.length;
        activeButtons[0] = new ActiveButton(bx, by, bw, 32, "Add");
        activeButtons[1] = new ActiveButton(bx + bw, by, bw, 32, "Remove");
        activeButtons[2] = new ActiveButton(bx + (bw * 2), by, bw, 32, "Up");
        activeButtons[3] = new ActiveButton(bx + (bw * 3), by, bw, 32, "Down");

        // Add one layer by default
        buttons.add(new ListButton(super.getCentre().x + stroke, super.getCentre().y + height - 32 - stroke, super.width - (stroke * 2), 32, "Layer" + (buttons.size() + 1)));
        selected = buttons.get(0);
    }

    public void moveUp() {
        if (buttons.size() > 1) {
            for (int i = 0; i < buttons.size() - 1; i++) {
                if (buttons.get(i) == selected) {
                    buttons.get(i).y -= 32;
                    buttons.get(i + 1).y += 32;

                    ListButton button = buttons.get(i);
                    buttons.set(i, buttons.get(i + 1));
                    buttons.set(i + 1, button);
//                    selected = buttons.get(i + 1);
                    return;
                }
            }
        }
    }

    public void moveDown() {
        if (buttons.size() > 1) {
            for (int i = 1; i < buttons.size(); i++) {
                if (buttons.get(i) == selected) {
                    buttons.get(i).y += 32;
                    buttons.get(i - 1).y -= 32;

                    ListButton button = buttons.get(i);
                    buttons.set(i, buttons.get(i - 1));
                    buttons.set(i - 1, button);
//                    selected = buttons.get(i - 1);
                    return;
                }
            }
        }
    }

    public void addlayer() {
        buttons.add(new ListButton(super.getCentre().x + stroke, super.getCentre().y + height - (32 * (buttons.size() + 1)) - stroke, super.width - (stroke * 2), 32, "Layer" + (buttons.size() + 1)));
    }

    public void removelayer() {
        if (buttons.size() > 1) {
            int i = buttons.indexOf(selected);
            buttons.remove(i);
            for (int j = i; j < buttons.size(); j++)
                buttons.get(j).y += 32;
            if (i > 0)
                selected = buttons.get(i - 1);
            else
                selected = buttons.get(i + 1);
        }
    }

    @Override
    public void click(int x, int y) {
        for (ListButton button : buttons) {
            if (button.IsClicked(x, y)) {
                button.click(x, y);
                selected = button;
            }
            else if (button != selected)
                button.Unclick(x, y);
        }
    }

    @Override
    public void press(int x, int y) {
        for (ActiveButton button : activeButtons) {
            if (button != null && button.IsClicked(x, y)) {
                button.press(x, y);
            }
        }
        if (activeButtons[0].IsPressed())
            addlayer();
        else if (activeButtons[1].IsPressed())
            removelayer();
        else if (activeButtons[2].IsPressed())
            moveUp();
        else if (activeButtons[3].IsPressed())
            moveDown();
    }


    @Override
    public void release(int x, int y) {
        for (ActiveButton button : activeButtons) {
            button.release(x, y);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (ActiveButton button : activeButtons) {
            if (button != null)
                button.paint(g);
        }
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).paint(g);
        }
    }
}
