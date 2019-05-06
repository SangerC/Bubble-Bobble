package drawables;

/**
 * The Enemy class for the arcade game.
 * 
 * Abstract class that provides methods for the specifc type of Character, Enemy, which 
 * moves across the screen and uses computer generated movement
 * 
 * Also don't forget to write javadocs for your classes and methods.
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */

public abstract class Enemy extends Character{

	public Enemy(double x, double y, double speed) {
		super(x, y, speed);
	}
}
