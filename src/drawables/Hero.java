package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

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
}
