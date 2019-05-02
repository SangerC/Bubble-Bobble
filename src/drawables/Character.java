package drawables;

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
