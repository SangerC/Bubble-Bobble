package drawables;

public class Hero extends Character{

	private int keyPressed;
	
	public Hero(double x, double y, double speed) {
		super(x, y, speed);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkCollision() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void setKeyPressed(int keyCode) {
		this.keyPressed=keyCode;
	}

}
