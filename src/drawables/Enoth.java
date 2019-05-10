package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import screen.Level;

public class Enoth extends Enemy{

	public Enoth(double x, double y, double speed, double fallSpeed, double jumpSpeed, Level level) {
		super(x, y, speed, fallSpeed, jumpSpeed, level);
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.translate(this.x, this.y);
		g2.setColor(Color.RED);
		g2.fillOval(0, 0, this.width, this.height);
		g2.translate(-this.x,-this.y);
	}
}
