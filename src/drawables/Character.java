package drawables;

/**
 * The character creation class for the arcade game.
 * 
 * Creates a specific Drawable called Character which is an abstract class that provides
 * methods for moving objects on the screen
 * 
 *
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */

public abstract class Character extends Drawable{
	
	protected double speed;
	protected double fallSpeed;
	
	public Character(double x, double y, double speed, double fallSpeed) {
		super(x, y);
		this.speed=speed;
		this.fallSpeed=fallSpeed;
	}

	public double getSpeed() {
		return this.speed;
	}
}
