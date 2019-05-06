package drawables;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.Timer;

public class Hero extends Character{

	private static final int JUMPLENGTH = 350;
	
	private int height;
	private int width;
	private int keyPressed;
	private int score;
	private Timer jumpTimer;
	
	public Hero(double x, double y, double speed, double fallSpeed) {
		super(x, y, speed, fallSpeed);
		this.height = 40;
		this.width =25;
		this.isFalling=true;
		this.isJumping=false;
		this.jumpTimer=new Timer(JUMPLENGTH,new JumpListener(this));		
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
		if(isJumping) {
			this.y-=.6;
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
			this.isJumping=true;
			jumpTimer.start();
		}
	}
	private class JumpListener implements ActionListener{
		
		private Hero hero;
		
		public JumpListener(Hero hero){
			this.hero=hero;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.hero.setIsJumping(false);
			this.hero.stopJumpTimer();
		}
	}
	public void setX(int x) {
		this.x=x;
	}

	public void setIsJumping(boolean isJumping) {
		this.isJumping=isJumping;
	}

	public void setFallSpeed(double fallSpeed) {
		this.fallSpeed=fallSpeed;	
	}
	public void stopJumpTimer(){
		this.jumpTimer.restart();
		this.jumpTimer.stop();
	}
	public double getFallSpeed() {
		return this.fallSpeed;
	}
}
