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
	private int score;
	private boolean isFalling;
	
	public Hero(double x, double y, double speed, double fallSpeed) {
		super(x, y, speed, fallSpeed);
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
		if(this.x>=1280){
			x=-10;
		}
		else if(x<=-40){
			x=1270;
		}
		if(y>=720){
			y=0;
		}
		else if(y<=-10){
			y=700;
		}
		
	}

	@Override
	public void update() {
		checkCollision();
		if(isFalling){
			this.y-=fallSpeed;
		}
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
		if(!isFalling){
			
		}
	}

	public void setX(int x) {
		this.x=x;
	}
}
