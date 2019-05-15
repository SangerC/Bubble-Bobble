package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.Timer;

public class Bubble extends Drawable{
	
	private static final double BUBBLEWIDTH=70;
	private static final double BUBBLEFLOATSPEED=2;
	private static final int MOVEDELAY=500;
	private static final int POPDELAY=200;
	
	private double width;
	private double bubbleSpeed;
	private boolean filled;
	private boolean moving;
	private boolean right;
	private Color bubbleColor;
	private Color bubbleFillColor;
	private Timer movingTimer;
	private Timer popTimer;

	public Bubble(double x, double y, Color bubbleColor, boolean right, double bubbleSpeed) {
		super(x, y);
		this.width=BUBBLEWIDTH;
		this.filled=false;
		this.moving=true;
		this.right=right;
		this.bubbleSpeed=bubbleSpeed;
		this.bubbleColor=bubbleColor;
		if(!right){
			this.x-=BUBBLEWIDTH/2;
		}
		this.bubbleFillColor=new Color(this.bubbleColor.getRed(),this.bubbleColor.getGreen(),this.bubbleColor.getBlue(),165);
		this.movingTimer = new Timer(MOVEDELAY,new MoveListener(this));
		this.popTimer = new Timer(POPDELAY,new PopListener(this));
		this.movingTimer.start();
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.translate(this.x, this.y);
		g2.setColor(this.bubbleColor);
		g2.drawOval(0,0,(int)this.width,(int)this.width);
		g2.setColor(bubbleFillColor);
		g2.fillOval(0,0,(int)this.width,(int)this.width);
		g2.translate(-this.x, -this.y);
	}

	@Override
	public void update() {
		if(this.moving==true){
			if(this.right){
				this.x+=bubbleSpeed;
			}
			else{
				this.x-=bubbleSpeed;
			}
		}
		else{
			if(this.y>10){
				this.y-=BUBBLEFLOATSPEED;
			}
			else if(!filled){
				this.y-=BUBBLEFLOATSPEED;
				if(this.y<-this.width){
					this.die=true;
				}
			}
			else {
				//popTimer.start();
			}
		}
	}
	
	private class MoveListener implements ActionListener{
		
		private Bubble bubble;
		
		public MoveListener(Bubble bubble){
			this.bubble=bubble;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.bubble.stopMoving();
		}	
	}
	
	private class PopListener implements ActionListener{
		
		private Bubble bubble;
		
		public PopListener(Bubble bubble){
			this.bubble=bubble;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.bubble.setDie(true);
		}	
	}
	
	public void stopMoving(){
		this.moving=false;
		this.movingTimer.stop();
		this.filled=true;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public boolean getFilled() {
		return this.filled;
	}
	
	public void setFilled(boolean filled) {
		this.filled=filled;
	}
}
