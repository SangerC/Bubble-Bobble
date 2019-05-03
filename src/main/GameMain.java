package main;

import java.awt.BorderLayout;

import screen.GameFrame;
import screen.Level;

public class GameMain {
	
	private GameFrame gameFrame;
	private Level level;
	private HeroListener heroListener;

	public GameMain(GameFrame gameFrame) {
		this.gameFrame=gameFrame;
		this.newGame();
		//this.heroListener=new HeroListener(level.getHero());
		//gameFrame.addKeyListener(heroListener);
	}

	
	
	
	public void newGame() {
		gameFrame.removeAll();
		this.level=new Level("levels/level0/level0");
		gameFrame.add(level,BorderLayout.CENTER);
		gameFrame.repaint();
	}
	
	
	
	
	
	
	
	
	
	
	
}
