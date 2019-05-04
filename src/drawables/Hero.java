package drawables;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Hero extends Character{

	private int height;
	private int width;
	private int keyPressed;
	
	public Hero(double x, double y, double speed) {
		super(x, y, speed);
		height = 40;
		width =25;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.translate(this.x, this.y);
		g2.setColor(Color.black);
		g2.fillRect(0,0,this.width,this.height);
		g2.translate(-this.x, -this.y);
	}

	@Override
	public void checkCollision() {
		if(this.x>=1280) {
			this.x=0;
		}
		else if(this.x<=0) {
			this.x=1280;
		}
		
	}

	@Override
	public void update() {
		
	}

	public void setKeyPressed(int keyCode) {
		this.keyPressed=keyCode;
	}
	public int getKeyPressed() {
		return this.keyPressed;
	}

	public void moveRight() {
		this.x+=this.speed;
	}
	public void moveLeft() {
		this.x-=this.speed;
	}

	public void jump() {
		
	}

	public void setX(int x) {
		this.x=x;
	}
}
