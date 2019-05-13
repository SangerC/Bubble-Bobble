package drawables;

import java.awt.Color;
import java.awt.Graphics2D;

public class Fruit extends Entity{
	private int width=20;
	private int height=20;
	private int score;
	
	public Fruit(double x, double y, double speed, double fallSpeed, double jumpSpeed, int score) {
		super(x,y,speed,fallSpeed,jumpSpeed);
		jumpSpeed=0;
		speed=0;
		this.score=score;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.translate(this.x, this.y);
		g2.setColor(Color.magenta);
		g2.fillRoundRect(0, 0, this.width, this.height, 2, 3);
		g2.translate(-this.x, -this.y);
	}

	public int getScore() {
		return this.score;
	}
	
}
