package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Hero extends Entity{
	
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
	}
	@Override
	public void draw(Graphics2D g2) {
		g2.translate(this.x, this.y);
		g2.setColor(Color.black);
		g2.fillRect(0,0,this.width,this.height);
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
			if(enemy.getBubble()==null&&!this.die) {
				this.die=true;
			}
			else {
				enemy.die();
			}
		}
	}
}
