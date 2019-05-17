package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import animations.Sprite;
import screen.Level;

/*
 * Class that provides methods for the specific type of Enemy, Enoth, which 
 * moves across the screen and uses computer generated movement and is the enemy that does not shoot bullets
 */
public class Inquisitor extends Enemy{

	public Inquisitor(double x, double y, double speed, double fallSpeed, double jumpSpeed, Level level) {
		super(x, y, speed, fallSpeed, jumpSpeed, level);
		this.sprite=new Sprite("assests/enemies/inquisitor/",level,this.width,this.height);
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.translate(this.x, this.y);
		this.sprite.draw(g2);
		g2.translate(-this.x,-this.y);
	}
	@Override
	public void update(){
		super.update();
		Random rand = new Random(); 
		if(this.y>=this.level.getHero().getY()-30&&this.y<=this.level.getHero().getY()+30&&rand.nextInt(100)==4&&this.bubble==null){
			if(this.x<this.level.getHero().getX()) {
				this.level.addBullet(new Bullet(this.x,this.y+this.height/2,Color.ORANGE,10,7));
			}
			else {
				this.level.addBullet(new Bullet(this.x,this.y+this.height/2,Color.ORANGE,10,-7));
			}
			
		}
	}
}
