package drawables;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The Enemy class for the arcade game.
 * 
 * Abstract class that provides methods for the specifc type of Character, Enemy, which 
 * moves across the screen and uses computer generated movement
 * 
 * 
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */

public abstract class Enemy extends Character{
	private int height;
	private int width;
	

	public Enemy(double x, double y, double speed, double fallSpeed, double jumpSpeed) {
		super(x, y, speed, fallSpeed, jumpSpeed);
		this.height=40;
		this.width=20;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.translate(this.x, this.y);
		g2.setColor(Color.RED);
		g2.fillOval((int)this.x, (int)this.y, this.width, this.height);
		g2.translate(-this.x,-this.y);
	}
	
	public void update(Hero hero) {
		if(hero.x<this.x) {
			this.moveLeft();;	
		}
		else if(hero.x>this.x) {
			this.moveRight();
		}
		if(hero.getY()>this.y) {
			this.jump();
		}
	}
}
