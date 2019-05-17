package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import screen.Level;

/*
 * Class that provides methods for the specific type of Enemy, Enoth, which 
 * moves across the screen and uses computer generated movement and is the enemy that does not shoot bullets
 */
public class Inquisitor extends Enemy{

	public Inquisitor(double x, double y, double speed, double fallSpeed, double jumpSpeed, Level level) {
		super(x, y, speed, fallSpeed, jumpSpeed, level);
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.translate(this.x, this.y);
		g2.setColor(Color.CYAN);
		g2.fillOval(0,0, this.width, this.height);
		g2.translate(-this.x,-this.y);
	}
	@Override
	public void update(){
		super.update();
		Random rand = new Random(); 
		if(this.y>=this.level.getHero().getY()-5&&this.y<=this.level.getHero().getY()+5&&rand.nextInt(100)==4){
			if(this.x<this.level.getHero().getX()) {
				this.level.addBullet(new Bullet(this.x,this.y+this.height/2,Color.gray,10,7));
			}
			else {
				this.level.addBullet(new Bullet(this.x,this.y+this.height/2,Color.gray,10,-7));
			}
			
		}
	}
	
	public Area getArea(){
		return new Area(new Ellipse2D.Double(this.x,this.y,this.width,this.height));
	}

}
