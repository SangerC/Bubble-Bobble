package drawables;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bubble extends Drawable{
	
	private static final double BUBBLEWIDTH=10;
	
	private double width;
	private boolean filled;
	private Color bubbleColor;

	public Bubble(double x, double y, Color bubbleColor) {
		super(x, y);
		this.width=BUBBLEWIDTH;
		this.filled=false;
		this.bubbleColor=bubbleColor;
	}

	@Override
	public void draw(Graphics2D g2) {
		

	}

	@Override
	public void checkCollision() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
