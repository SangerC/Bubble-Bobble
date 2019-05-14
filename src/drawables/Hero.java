package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.Timer;

public class Hero extends Entity{
	
	protected static final int INVULNERABILITYDELAY = 2000;
	
	private int keyPressed;
	private int score;
	private Color bubbleColor = Color.blue;
	private double bubbleSpeed=5;
	private int life;

	public Hero(double x, double y, double speed, double fallSpeed, double jumpSpeed) {
		super(x, y, speed, fallSpeed, jumpSpeed);
		this.height = 40;
		this.width =25;
		this.life=5;
		this.invulnerableTimer=new Timer(INVULNERABILITYDELAY, new InvulnerabilityListener(this));
	}
	@Override
	public void draw(Graphics2D g2) {
		Random rand = new Random();
		g2.translate(this.x, this.y);
		if(this.vulnerable||rand.nextInt(10)>1) {
			g2.setColor(Color.black);
			g2.fillRect(0,0,this.width,this.height);
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
		Rectangle h= new Rectangle((int)this.x, (int)this.y, this.width, this.height);
		Rectangle f = new Rectangle((int)fruit.getX(), (int) fruit.getY(), (int)fruit.getWidth(), (int)fruit.getHeight());
		if(h.getBounds2D().intersects(f)&&!fruit.isFalling) {
			fruit.setDie(true);
			this.score+=fruit.getScore();
			System.out.println("Score = "+this.score);
		}
	}
	public void checkCollision(Enemy enemy){
		Rectangle a= new Rectangle((int)this.x,(int)this.y,this.width,this.height);
		Rectangle b= new Rectangle((int)enemy.getX(),(int)enemy.getY(),(int)enemy.getWidth(),(int)enemy.getHeight());
		if(a.getBounds2D().intersects(b)){
			if(enemy.getBubble()==null) {
				this.die();
			}
			else {
				enemy.die();
			}
		}
	}
	public void checkCollision(Bullet bill) {
		Rectangle h = new Rectangle((int)this.getX(), (int)this.getY(), this.width, this.height);
		Rectangle b = new Rectangle((int)bill.getX(), (int)bill.getY(), bill.getWidth(), bill.getWidth());
		if(h.getBounds().intersects(b)) {
			this.die();
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
}
