package drawables;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javafx.geometry.Point2D;

public class Hero extends Character{

	private int keyPressed;
	private Rectangle2D.Double rect= new Rectangle2D.Double();
	
	public Hero(double x, double y, double speed) {
		super(x, y, speed);
	}

	@Override
	public void draw() {
		rect.x= this.x;
		rect.y= this.y;
		rect.height=15;
		rect.width=10;
		
		
		
		
	}

	@Override
	public void checkCollision() {
		
		
	}

	@Override
	public void update() {
		rect.x=this.x;
		rect.y=this.y;
		
	}

	public void setKeyPressed(int keyCode) {
		this.keyPressed=keyCode;
	}
	public int getKeyPressed() {
		return this.keyPressed;
	}

}
