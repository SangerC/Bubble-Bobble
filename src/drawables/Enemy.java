package drawables;

/**
 * The Enemy class for the arcade game.
 * 
 * Abstract class that provides methods for the specifc type of Character, Enemy, which 
 * moves across the screen and uses computer generated movement
 * 
 * 
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */

public abstract class Enemy extends Character{

	public Enemy(double x, double y, double speed, double fallSpeed, double jumpSpeed) {
		super(x, y, speed, fallSpeed, jumpSpeed);
	}
}
