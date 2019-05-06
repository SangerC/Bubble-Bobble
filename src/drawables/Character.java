package drawables;

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
