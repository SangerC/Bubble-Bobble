package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Inquisitor extends Enemy{

	public Inquisitor(double x, double y, double speed, double fallSpeed, double jumpSpeed) {
		super(x, y, speed, fallSpeed, jumpSpeed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.translate(this.x, this.y);
		g2.setColor(Color.CYAN);
		g2.fillOval(0,0, this.width, this.height);
		g2.translate(-this.x,-this.y);
	}
	@Override
	public void update(Hero hero){
		super.update(hero);
		Random rand = new Random(); 
		if(this.y>=hero.getY()-1.5&&this.y<=hero.getY()+1.5&&rand.nextInt(100)==4){
			System.out.println("SHot");
		}
	}

}
