package main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import screen.GameFrame;
import screen.Level;

public class GameMain {
	
	private GameFrame gameFrame;
	private Level level;
	private HeroListener heroListener;
	private boolean running=false;
	private boolean paused=false;

	public GameMain(GameFrame gameFrame) {
		this.gameFrame=gameFrame;
		this.newGame();
		//this.run();
	}

	
	
	
	public void newGame() {
		this.gameFrame.getContentPane().removeAll();
		this.level=new Level("levels/level0/level0");
		this.level.setPreferredSize(new Dimension(1280,720));
		this.gameFrame.add(this.level,BorderLayout.CENTER);
		this.heroListener=new HeroListener(level.getHero());
		this.gameFrame.addKeyListener(this.heroListener);
	}
	
	
	private void run(){
		while(running) {
			
			
			while(!paused){

				
				
			}
			
			
		}
	}
	
	
	public void moveLevel(int levelNumber){
		
		
		
		
		
	}
	
	
	
	
	
}
