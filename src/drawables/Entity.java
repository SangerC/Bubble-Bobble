package drawables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.Timer;

import animations.Sprite;

/**
 * The character creation class for the arcade game.
 * 
 * Creates a specific Drawable called Character which is an abstract class that provides
 * methods for moving objects on the screen
 * 
 *
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */

public abstract class Entity extends Drawable{
	
	protected static final int JUMPLENGTH = 750;
	
	protected double speed;
	protected double fallSpeed;
	protected double jumpSpeed;
	protected boolean isFalling;
	protected boolean isJumping;
	protected boolean facingRight;
	protected boolean vulnerable;
	protected Sprite sprite;
	protected int height;
	protected int width;
	protected Timer jumpTimer;
	protected Timer invulnerableTimer;
	
	public Entity(double x, double y, double speed, double fallSpeed, double jumpSpeed) {
		super(x, y);
		this.speed=speed;
		this.fallSpeed=fallSpeed;
		this.jumpSpeed=jumpSpeed;
		this.isFalling=true;
		this.isJumping=false;
		this.vulnerable=true;
		this.jumpTimer=new Timer(JUMPLENGTH,new JumpListener(this));
	}

	public double getSpeed() {
		return this.speed;
	}
	
	public void moveRight() {
		this.x+=this.speed;
		this.facingRight=true;
	}
	
	public void moveLeft() {
		this.x-=this.speed;
		this.facingRight=false;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public double getHeight() {
		return this.width;
	}
	
	public void checkCollision(ArrayList<Obstacle> obstacles) {
		this.isFalling=true;
		for(Obstacle o: obstacles){
			if((this.x+this.width>o.getX())&&
			   (this.x<(o.getX()+o.getWidth()))&&
			   ((this.y+this.height)>=o.getY())&&
			   ((this.y+this.height)<=(o.getY()+3))){
				this.isFalling=false;
			}
		}
	}
	
	public void update() {
		if(isFalling){
			this.y+=this.fallSpeed;
		}
		if(isJumping) {
			this.y-=this.jumpSpeed;
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
	public boolean getFacingRight() {
		return this.facingRight;
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
	
	public void jump() {
		if(!isFalling){
			this.isJumping=true;
			jumpTimer.start();
			if(this.sprite!=null){
				this.sprite.setCurrentAnimation("jump");
			}
		}
	}

	public void setIsJumping(boolean isJumping) {
		this.isJumping=isJumping;
	}
	
	protected class JumpListener implements ActionListener{
		
		private Entity character;
		
		public JumpListener(Entity character){
			this.character=character;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.character.setIsJumping(false);
			this.character.stopJumpTimer();
		}
	}
	protected class InvulnerabilityListener implements ActionListener{
		
		private Entity character;
		
		public InvulnerabilityListener(Entity character){
			this.character=character;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.character.setVulnerable(true);
			this.character.stopInvlnerabilityTimer();
		}
	}
	public boolean getIsFalling() {
		return this.isFalling;
	}
	public void stopInvlnerabilityTimer() {
		this.invulnerableTimer.stop();
	}
	public void setVulnerable(boolean vulnerable) {
		this.vulnerable=vulnerable;
	}
	public boolean getVulnerable(){
		return this.vulnerable;
	}
	public abstract Area getArea();
}
