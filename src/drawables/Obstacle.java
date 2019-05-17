package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * The Bubble class for the arcade game.
 * 
 *  Class that creates, updates and handles Obstacle functions in the game
 * and is a type of drawable
 * 
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */
public class Obstacle extends Drawable{
	
	private int height;
	private int width;
	private Color color;
	private Image background;
	
	public Obstacle(double x, double y, int width, int height, Color color, String background) {
		super(x, y);
		this.width=width;
		this.height=height;
		this.color=color;
		if(!background.equals("none")) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			this.background= toolkit.getImage(background);
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.translate(this.x, this.y);
		g2.setColor(this.color);
		g2.fillRect(0,0,this.width,this.height);
		g2.translate(-this.x, -this.y);
	}

	@Override
	public void update() {
	}

	public double getWidth() {
		return this.width;
	}

}
