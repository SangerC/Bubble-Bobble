package drawables;

public abstract class Drawable {

	private double x;
	private double y;
	
	
	public Drawable(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public abstract void draw();
	public void move(double x, double y) {
		this.x=x;
		this.y=y;
	}
	public abstract void checkCollision();
	public void remove() {
		
	}
	public abstract void update();
	
	
	
}
