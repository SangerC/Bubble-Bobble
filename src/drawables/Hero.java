package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import animations.Sprite;
import screen.Level;

/**
 * The Hero class for the arcade game.
 * 
 *  Class that creates, updates and handles hero functions in the game
 * 
 * 
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */
public class Hero extends Entity{
	
	protected static final int INVULNERABILITYDELAY = 2000;
	
	private int keyPressed;
	private int score;
	private Color bubbleColor = Color.blue;
	private double bubbleSpeed=5;

	public Hero(double x, double y, double speed, double fallSpeed, double jumpSpeed, Level level, String animationFolder) {
		super(x, y, speed, fallSpeed, jumpSpeed);
		this.height = 75;
		this.width =75;
		this.invulnerableTimer=new Timer(INVULNERABILITYDELAY, new InvulnerabilityListener(this));
		this.sprite=new Sprite(animationFolder,level, this.width, this.height);
		this.canAct=true;
	}
	@Override
	public void draw(Graphics2D g2) {
		Random rand = new Random();
		g2.translate(this.x, this.y);
		if(this.vulnerable||rand.nextInt(10)>1) {
			this.sprite.draw(g2);
		}
		g2.translate(-this.x, -this.y);
	}
	@Override
	public void update() {
		switch(keyPressed){
			case 37:
				this.moveLeft();
				break;
			case 39:
				this.moveRight();
				break;
		}
		super.update();
		this.updateAnimation();
	}
	private void updateAnimation(){
		if(this.sprite.getCurrentAnimation().equals("die")) {
			if(this.sprite.getCurrentAnimationIndex()==this.sprite.getAnimations().get(this.sprite.getCurrentAnimation()).size()-1){
				this.dieHelper();
			}
		}
		else if(this.sprite.getCurrentAnimation().equals("shootRight")||this.sprite.getCurrentAnimation().equals("shootLeft")){
				if(this.sprite.getCurrentAnimationIndex()==this.sprite.getAnimations().get(this.sprite.getCurrentAnimation()).size()-1){
					this.sprite.setCurrentAnimation("idleRight");
				}
		}
		else if(this.sprite.getCurrentAnimation().equals("jump")){
				if(this.sprite.getCurrentAnimationIndex()==this.sprite.getAnimations().get(this.sprite.getCurrentAnimation()).size()-1){
					this.sprite.setCurrentAnimation("idleRight");
				}
		}
		else{
			if(this.isFalling&&this.sprite.getCurrentAnimation()!="fall") {
				this.sprite.setCurrentAnimation("fall");
			}
			else if(keyPressed!=0){
				if(this.facingRight&&this.sprite.getCurrentAnimation()!="runRight"&&!this.isFalling) {
					this.sprite.setCurrentAnimation("runRight");
				}
				else if(!this.facingRight&&this.sprite.getCurrentAnimation()!="runLeft"&&!this.isFalling) {
					this.sprite.setCurrentAnimation("runLeft");
				}
			}
			else if(this.sprite.getCurrentAnimation()!="idleLeft"&&this.sprite.getCurrentAnimation()!="idleRight"&&!this.isFalling){
				if(this.facingRight) {
					this.sprite.setCurrentAnimation("idleRight");
				}
				else{
					this.sprite.setCurrentAnimation("idleLeft");
				}
			}
		}
	}
	public void setKeyPressed(int keyCode) {
		this.keyPressed=keyCode;
	}
	public int getKeyPressed() {
		return this.keyPressed;
	}
	public Color getBubbleColor() {
		return this.bubbleColor;
	}
	public double getBubbleSpeed() {
		return this.bubbleSpeed;
	}

	public void checkCollision(Fruit fruit) {
		if(this.getArea().getBounds2D().intersects(fruit.getArea().getBounds2D())&&!fruit.isFalling) {
			fruit.setDie(true);
			this.score+=fruit.getScore();
		}
	}
	public void checkCollision(Enemy enemy){
		if(this.getArea().getBounds2D().intersects(enemy.getArea().getBounds2D())){
			if(enemy.getBubble()==null) {
				this.die();
			}
			else {
				enemy.die();
			}
		}
	}
	@Override
	public void checkCollision(ArrayList<Obstacle> obstacles) {
		this.isFalling=true;
		for(Obstacle o: obstacles){
			if((this.x+this.width/2>o.getX())&&
			   (this.x+this.width/2<(o.getX()+o.getWidth()))&&
			   ((this.y+this.height)>=o.getY())&&
			   ((this.y+this.height)<=(o.getY()+3))){
				this.isFalling=false;
			}
		}
	}
	public void checkCollision(Bullet bill) {
		if(this.getArea().getBounds2D().intersects(bill.getArea().getBounds2D())) {
			this.die();
			bill.setDie(true);
		}
	}
	private void die(){
		this.sprite.setCurrentAnimation("die");
		this.vulnerable=false;
		this.canAct=false;
		this.keyPressed=0;
	}
	private void dieHelper(){
		this.die=true;
		this.vulnerable=false;
		this.invulnerableTimer.restart();
		this.sprite.setCurrentAnimation("idleRight");
	}
	public int getScore(){
		return this.score;
	}
	public Area getArea() {
		return new Area(new Rectangle((int)this.x+this.width/4,(int)this.y+this.height/8,this.width-this.width/2,this.height-this.height/8));
	}
	public Sprite getSprite() {
		return this.sprite;
	}
}
