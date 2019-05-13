package drawables;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Drawable{

	private Color color;
	private int width;
	private double speed;
	
	
	public Bullet(double x, double y, Color color, int width, double speed) {
		super(x, y);
		this.color=color;
		this.width=width;
		this.speed=speed;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.translate(this.x, this.y);
		g2.setColor(this.color);
		g2.fillOval(0, 0, this.width, this.width);
		g2.translate(-this.x,-this.y);
	}

	@Override
	public void update() {
		this.x+=this.speed;
		if(this.x>1280){
			this.die=true;
		}
		else if(this.x<0){
			this.die=false;
		}
		
	}

	public int getWidth() {
		return this.width;
	}
}
