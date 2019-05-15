package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.Random;

import javax.swing.Timer;

import animations.Sprite;
import screen.Level;

public class Hero extends Entity{
	
	protected static final int INVULNERABILITYDELAY = 2000;
	
	private int keyPressed;
	private int score;
	private Color bubbleColor = Color.blue;
	private double bubbleSpeed=5;
	private int life;

	public Hero(double x, double y, double speed, double fallSpeed, double jumpSpeed, Level level, String animationFolder) {
		super(x, y, speed, fallSpeed, jumpSpeed);
		this.height = 40;
		this.width =25;
		this.life=5;
		this.invulnerableTimer=new Timer(INVULNERABILITYDELAY, new InvulnerabilityListener(this));
		this.sprite=new Sprite(animationFolder,level);
	}
	@Override
	public void draw(Graphics2D g2) {
		Random rand = new Random();
		g2.translate(this.x, this.y);
		if(this.vulnerable||rand.nextInt(10)>1) {
			this.sprite.draw(g2);
//			g2.setColor(Color.black);
//			g2.fillRect(0,0,this.width,this.height);
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
		updateAnimation();
	}
	private void updateAnimation(){
		if(this.sprite.getCurrentAnimation().equals("die")
			&&this.sprite.getCurrentAnimationIndex()==this.sprite.getAnimations().get(this.sprite.getCurrentAnimation()).size()-1){
			this.die();
		}
		else if(this.sprite.getCurrentAnimation().equals("shoot")
				&&this.sprite.getCurrentAnimationIndex()==this.sprite.getAnimations().get(this.sprite.getCurrentAnimation()).size()-1){
			this.sprite.setCurrentAnimation("idle");
		}
		else if(this.sprite.getCurrentAnimation().equals("shoot")
				&&this.sprite.getCurrentAnimationIndex()==this.sprite.getAnimations().get(this.sprite.getCurrentAnimation()).size()-1){
			this.sprite.setCurrentAnimation("idle");
		}
		if(this.sprite.getCurrentAnimation()!="shoot"&&this.sprite.getCurrentAnimation()!="die") {
			if(this.isFalling&&this.sprite.getCurrentAnimation()!="fall") {
				this.sprite.setCurrentAnimation("fall");
			}
			else if(keyPressed!=0){
				if(this.facingRight&&this.sprite.getCurrentAnimation()!="runRight") {
					this.sprite.setCurrentAnimation("runRight");
				}
				else if(!this.facingRight&&this.sprite.getCurrentAnimation()!="runLeft") {
					this.sprite.setCurrentAnimation("runLeft");
				}
			}
			else{
				this.sprite.setCurrentAnimation("idle");
			}
		}
		this.sprite.update();
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
	public void setLife(int life) {
		this.life=life;
	}
	public int getLife() {
		return this.life;
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
				this.sprite.setCurrentAnimation("die");
				this.invulnerableTimer.restart();
			}
			else {
				enemy.die();
			}
		}
	}
	public void checkCollision(Bullet bill) {
		if(this.getArea().getBounds2D().intersects(bill.getArea().getBounds2D())) {
			this.sprite.setCurrentAnimation("die");
			this.invulnerableTimer.restart();
			bill.setDie(true);
		}
	}
	private void die(){
		this.die=true;
		this.vulnerable=false;
		this.invulnerableTimer.restart();
	}
	public int getScore(){
		return this.score;
	}
	public Area getArea() {
		return new Area(new Rectangle((int)this.x,(int)this.y,this.width,this.height));
	}
}
