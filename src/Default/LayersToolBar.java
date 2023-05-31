package Default;

import Buttons.ActiveButton;
import Buttons.ListButton;
import Canvas.Shape;
import Canvas.Stack;
import Canvas.Queue;
import Interfaces.DrawButtons;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;

public class LayersToolBar extends ToolBar implements DrawButtons {
    ActiveButton[] activeButtons;
    private LinkedList<ListButton> buttons;
    public ListButton selected;
    public Stack<Shape> selectedStack;

    public LayersToolBar() {
        super();
    }

    public LayersToolBar(int x, int y, int width, int height, Color rectColor, Color lineColor, int stroke, Board b) {
        super(x, y, width, height, rectColor, lineColor, stroke, b);
        buttons = new LinkedList<>();

        // Add the adding removing buttons
        activeButtons = new ActiveButton[4];
        int bx = x + stroke - 1;
        int by = y + stroke;
        int bw = width / activeButtons.length;
        activeButtons[0] = new ActiveButton(bx, by, bw, 32, "Add");
        activeButtons[1] = new ActiveButton(bx + bw, by, bw, 32, "Remove");
        activeButtons[2] = new ActiveButton(bx + (bw * 2), by, bw, 32, "Up");
        activeButtons[3] = new ActiveButton(bx + (bw * 3), by, bw, 32, "Down");

        // Add one layer by default
        if (buttons.size() == 0)
            buttons.add(new ListButton(super.getCentre().x + stroke, super.getCentre().y + height - 32 - stroke, super.width - (stroke * 2), 32, "Layer" + (buttons.size() + 1)));
        if (b.layer.size() == 0)
            b.layer.add(new Stack<>());
        if (b.redo.size() == 0)
            b.redo.add(new Queue<>());
        selected = buttons.get(0);
        selectedStack = b.layer.get(0);
    }

    public void moveUp() {
        if (buttons.size() > 1) {
            for (int i = 0; i < b.layer.size() - 1; i++) {
                if (b.layer.get(i) == selectedStack) {
                    Stack temp = b.layer.get(i);
                    b.layer.set(i, b.layer.get(i + 1));
                    b.layer.set(i + 1, temp);
                    Queue temp1 = b.redo.get(i);
                    b.redo.set(i, b.redo.get(i + 1));
                    b.redo.set(i + 1, temp1);

                    break;
                }
            }
            for (int i = 0; i < buttons.size() - 1; i++) {
                if (buttons.get(i) == selected) {
                    buttons.get(i).y -= 32;
                    buttons.get(i + 1).y += 32;

                    ListButton button = buttons.get(i);
                    buttons.set(i, buttons.get(i + 1));
                    buttons.set(i + 1, button);
                    return;
                }
            }
        }
    }

    public void moveDown() {
        if (buttons.size() > 1) {
            for (int i = 1; i < b.layer.size(); i++) {
                if (b.layer.get(i) == selectedStack) {
                    Stack temp = b.layer.get(i);
                    b.layer.set(i, b.layer.get(i - 1));
                    b.layer.set(i - 1, temp);
                    Queue temp1 = b.redo.get(i);
                    b.redo.set(i, b.redo.get(i - 1));
                    b.redo.set(i - 1, temp1);
                    break;
                }
            }
            for (int i = 1; i < buttons.size(); i++) {
                if (buttons.get(i) == selected) {
                    buttons.get(i).y += 32;
                    buttons.get(i - 1).y -= 32;

                    ListButton button = buttons.get(i);
                    buttons.set(i, buttons.get(i - 1));
                    buttons.set(i - 1, button);
                    return;
                }
            }
        }
    }

    public void addlayer() {
        buttons.add(new ListButton(super.getCentre().x + stroke, super.getCentre().y + height - (32 * (buttons.size() + 1)) - stroke, super.width - (stroke * 2), 32, "Layer" + (buttons.size() + 1)));
        b.layer.add(new Stack<>());
        b.redo.add(new Queue<>());
        System.out.println(b.layer.size());
        selected = buttons.get(buttons.size() - 1);
        selectedStack = b.layer.get(b.layer.size() - 1);
    }

    public void removelayer() {
        if (buttons.size() > 1) {
            int i = buttons.indexOf(selected);
            buttons.remove(i);
            b.layer.remove(i);
            for (int j = i; j < buttons.size(); j++)
                buttons.get(j).y += 32;
            if (i > 0) {
                selected = buttons.get(i - 1);
                selectedStack = b.layer.get(i - 1);
            }
            else {
                selected = buttons.get(i);
                selectedStack = b.layer.get(i);
            }
        }
    }

    @Override
    public void click(int x, int y) {
        int i = 0;
        for (ListButton button : buttons) {
            if (button.IsClicked(x, y)) {
                button.click(x, y);
                selected = button;
                selectedStack = b.layer.get(i);
            }
            else if (button != selected)
                button.Unclick(x, y);
            i++;
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
        selected.showSelection();
        for (ActiveButton button : activeButtons) {
            if (button != null)
                button.paint(g);
        }
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).paint(g);
        }
    }

//    public void add_layers(int size){
//        for (int i = 0; i < size; i++) {
//            buttons.add(new ListButton(super.getCentre().x + stroke, super.getCentre().y + height - (32 * (i + 2)) - stroke, super.width - (stroke * 2), 32, "Layer" + (buttons.size() + 1)));
//        }
//    }
    public void removeLayer_Serialization() {
        if (buttons.size() > 1) {
            int i = 0;
            buttons.remove(i);
            b.layer.remove(i);
            for (int j = i; j < buttons.size(); j++)
                buttons.get(j).y += 32;

            selected = buttons.get(i);
            selectedStack = b.layer.get(i);

    }
}


}
