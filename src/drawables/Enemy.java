package drawables;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import screen.Level;

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

public abstract class Enemy extends Entity{
	
	private static final int MOVEDELAY =2000;
	private static final int BUBBLEDELAY =6000;
	
	private Timer moveTimer;
	private Timer bubbleTimer;
	private boolean moveChange;
	protected Level level;
	private Bubble bubble;

	public Enemy(double x, double y, double speed, double fallSpeed, double jumpSpeed, Level level) {
		super(x, y, speed, fallSpeed, jumpSpeed);
		this.height=40;
		this.width=20;
		this.moveChange=true;
		this.moveTimer=new Timer(MOVEDELAY,new MoveListener(this));
		this.bubbleTimer=new Timer(BUBBLEDELAY,new BubbleListener(this));
		this.level=level;
	}
	
	@Override
	public void update() {
		if(this.bubble==null){
			super.update();
			Random rand = new Random(); 
			if(this.y>this.level.getHero().getY()+3&&rand.nextInt(500)==1) {
				this.jump();
			}
			else if(this.y>this.level.getHero().getY()-3&&this.moveChange){
				if(this.level.getHero().getX()>this.x&&rand.nextInt(100)>25){
					this.moveChange=false;
					this.moveTimer.restart();
					this.facingRight=true;
				}
				else{
					this.moveChange=false;
					this.moveTimer.restart();
					this.facingRight=false;
				}
			}
			if(this.facingRight) {
				this.moveRight();
			}
			else {
				this.moveLeft();
			}
		}
		else{
			this.x=this.bubble.getX()+this.bubble.getWidth()/2-this.width/2;
			this.y=this.bubble.getY()+this.bubble.getWidth()/2-this.height/2;
			System.out.println(this.bubble.getDie());
		}
		
	}
	
	public void checkCollision(Bubble bubble){
		Rectangle a= new Rectangle((int)this.x,(int)this.y,this.width,this.height);
		Rectangle b= new Rectangle((int)bubble.getX(),(int)bubble.getY(),(int)bubble.getWidth(),(int)bubble.getWidth());
		if(a.getBounds2D().intersects(b)){
			this.bubble=bubble;
			this.bubble.setFilled(true);
			this.bubbleTimer.restart();
		}
	}
	
	
	private class MoveListener implements ActionListener{

		private Enemy enemy;
		
		public MoveListener(Enemy enemy) {
			this.enemy=enemy;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.enemy.allowMoveChange();
		}
		
	}
	
	private class BubbleListener implements ActionListener{

		private Enemy enemy;
		
		public BubbleListener(Enemy enemy) {
			this.enemy=enemy;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.enemy.freeFromBubble();
		}
		
	}

	public void allowMoveChange() {
		this.moveTimer.stop();
		this.moveChange=true;
	}
	
	public Bubble getBubble() {
		return this.bubble;
	}
	
	public void freeFromBubble(){
		this.bubble.setFilled(false);
		this.bubble.setDie(true);
		this.bubble=null;
		this.bubbleTimer.stop();
		this.isFalling=true;
	}
	public void die() {
		Random rand = new Random(); 
		this.level.addFruit(new Fruit(this.x,this.y,0,3,0,rand.nextInt(900)+100));
		this.die=true;
	}
}
