package drawables;

import java.awt.Color;
import java.awt.Graphics2D;

public class Fruit extends Character {
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
		g2.setColor(Color.ORANGE);
		g2.drawRoundRect(0, 0, this.width, this.height, 20, 30);
		g2.translate(this.x, this.y);

	}

	public int Score() {
		return this.score;
	}

}