package drawables;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Hero extends Character{

	private int height;
	private int width;
	private int keyPressed;
	private int score;
	private boolean isFalling;
	
	public Hero(double x, double y, double speed, double fallSpeed) {
		super(x, y, speed, fallSpeed);
		this.height = 40;
		this.width =25;
		this.isFalling=true;
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
		
	}

	public void checkCollision(ArrayList<Obstacle> obstacles) {
		this.isFalling=true;
		for(Obstacle o: obstacles){
			if((this.x+this.width>o.getX())&&
			   (this.x<(o.getX()+o.getWidth()))&&
			   ((this.y+this.height)>=o.getY())&&
			   ((this.y+this.height)<=(o.getY()+20))){
				this.isFalling=false;
			}
		}
	}
	
	
	@Override
	public void update() {
		if(isFalling){
			this.y+=fallSpeed;
		}
		if(this.x>1290) {
			this.x=-10;
		}
		else if(this.x<-40){
			this.x=1280;
		}
		if(this.y>730) {
			this.y=0;
		}
		else if(this.y<-10){
			this.y=720;
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
