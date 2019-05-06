package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

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
	public void checkCollision() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
