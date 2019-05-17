package drawables;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 * The Fruit class for the arcade game.
 * 
 *  Class that creates, updates and handles fruit functions in the game
 * 
 * 
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */
public class Fruit extends Entity{
	
	private static final String LOWSCOREFRUIT = "assests/fruit/strawberry.png";
	private static final String HIGHSCOREFRUIT = "assests/fruit/pineapple.png";
	
	private Image lowScoreFruit; 
	private Image highScoreFruit; 
	
	private int score;
	
	public Fruit(double x, double y, double speed, double fallSpeed, double jumpSpeed, int score) {
		super(x,y,speed,fallSpeed,jumpSpeed);
		this.width=30;
		this.height=30;
		jumpSpeed=0;
		speed=0;
		this.score=score;
		this.invulnerableTimer=new Timer(JUMPLENGTH,new JumpListener(this));
		try {
			this.lowScoreFruit = ImageIO.read(new File(LOWSCOREFRUIT));
			this.highScoreFruit = ImageIO.read(new File(HIGHSCOREFRUIT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.translate(this.x, this.y-this.height/8);
		if(this.score>800) {
			g2.drawImage(this.highScoreFruit,0,0,this.width,this.height,null);
		}
		else {
			g2.drawImage(this.lowScoreFruit,0,0,this.width,this.height,null);
		}
		g2.translate(-this.x, -this.y);
	}
	
	public int getScore() {
		return this.score;
	}
	
	public Area getArea(){
		return new Area(new Rectangle((int)this.x,(int)this.y,this.width,this.height));
	}
	
}
