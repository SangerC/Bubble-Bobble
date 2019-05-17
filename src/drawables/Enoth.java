package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import screen.Level;

/**
 * A specific type of enemy class for the arcade game.
 * 
 * Class that provides methods for the specific type of Enemy, Enoth, which 
 * moves across the screen and uses computer generated movement and is the enemy that does not shoot bullets
 * 
 * 
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */
public class Enoth extends Enemy{

	public Enoth(double x, double y, double speed, double fallSpeed, double jumpSpeed, Level level){
		super(x, y, speed, fallSpeed, jumpSpeed, level);
	}

	@Override
	public void draw(Graphics2D g2){
		g2.translate(this.x, this.y);
		g2.setColor(Color.RED);
		g2.fillOval(0, 0, this.width, this.height);
		g2.translate(-this.x,-this.y);
	}
	public Area getArea(){
		return new Area(new Ellipse2D.Double(this.x,this.y,this.width,this.height));
	}
}
