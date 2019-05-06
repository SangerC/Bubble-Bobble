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
	
	public Character(double x, double y, double speed) {
		super(x, y);
		this.speed=speed;
	}

	public double getSpeed() {
		return this.speed;
	}
}
