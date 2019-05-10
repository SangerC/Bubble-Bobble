package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

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
	
	private static final int MOVEDELAY =2000;
	
	private Timer moveTimer;
	private boolean moveChange;

	public Enemy(double x, double y, double speed, double fallSpeed, double jumpSpeed) {
		super(x, y, speed, fallSpeed, jumpSpeed);
		this.height=40;
		this.width=20;
		this.moveChange=true;
		this.moveTimer=new Timer(MOVEDELAY,new MoveListener(this));
	}
		
	public void update(Hero hero) {
		this.update();
		System.out.println(this.isJumping);
		Random rand = new Random(); 
		if(this.y>hero.getY()+3&&rand.nextInt(500)==1) {
			this.jump();
		}
		else if(this.y>hero.getY()-3&&this.moveChange){
			if(hero.getX()>this.x&&rand.nextInt(100)>25){
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

	public void allowMoveChange() {
		this.moveTimer.stop();
		this.moveChange=true;
	}
	
	
}
