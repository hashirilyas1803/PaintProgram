package Buttons;

import Default.RectangleTemplate;
import Interfaces.Interactibility;

import java.awt.*;

public class Button implements Interactibility
{
	public int x;
	public int y;
	protected int width;
	protected int height;
	private Color rectColor;
	private Color lineColor;
	protected Image image_depressed;
	protected Image image_pressed;
	protected Image current_image;
	protected boolean pressed;
	
	public Button(int x, int y, int width, int height, Image i_depressed, Image i_pressed)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		image_depressed = i_depressed.getScaledInstance(width, height, Image.SCALE_FAST);
		image_pressed = i_pressed.getScaledInstance(width, height, Image.SCALE_FAST);
		current_image = i_depressed;
	}

	public Button(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rectColor = Color.WHITE;
		lineColor = Color.LIGHT_GRAY;
	}

	public Image GetImage()
	{
		return current_image;
	}
	
	public Boolean IsPressed()
	{
		return pressed;
	}
	
	public void SetPressed(boolean pressed)
	{
		this.pressed = pressed;
	}



	public void setCurrent_image(Image current_image) {
		this.current_image = current_image;
	}

	public boolean IsClicked(int x, int y) {
		if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
		{
			return true;
		}
		return false;
	}

	@Override
	public void press(int x, int y) {
		pressed = true;
	}

	@Override
	public void release(int x, int y) {
		pressed = false;
	}

	public void Unclick(int x, int y) {
		current_image = image_depressed;
		pressed = false;
		if (image_depressed == null)
			setLineColor(Color.LIGHT_GRAY);
	}

	public boolean IsReleased(int x, int y) {
		if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
		{
			pressed = false;
			current_image = image_depressed;
		}
		return pressed;
	}

	public Color getRectColor() {
		return rectColor;
	}

	public void setRectColor(Color rectColor) {
		this.rectColor = rectColor;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	@Override
	public void click(int x, int y) {
		if (IsClicked(x, y)) {
			current_image = image_pressed;
			pressed = true;
		}
	}

	public void paint(Graphics g) {
		RectangleTemplate rect = new RectangleTemplate(x, y , width, height, rectColor, lineColor, 2);
		rect.paint(g);
	}
}
