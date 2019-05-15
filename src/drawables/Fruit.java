package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.Timer;

public class Fruit extends Entity{
	
	private int score;
	
	public Fruit(double x, double y, double speed, double fallSpeed, double jumpSpeed, int score) {
		super(x,y,speed,fallSpeed,jumpSpeed);
		this.width=20;
		this.height=30;
		jumpSpeed=0;
		speed=0;
		this.score=score;
		this.invulnerableTimer=new Timer(JUMPLENGTH,new JumpListener(this));
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.translate(this.x, this.y);
		g2.setColor(Color.magenta);
		g2.fillRect(0, 0, this.width, this.height);
		g2.translate(-this.x, -this.y);
	}
	
	public int getScore() {
		return this.score;
	}
	
	public Area getArea(){
		return new Area(new Rectangle((int)this.x,(int)this.y,this.width,this.height));
	}
	
}
