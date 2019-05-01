package main;

import java.awt.BorderLayout;

import screen.GameFrame;
import screen.Level;

public class GameMain {
	
	private GameFrame gameFrame;
	private Level level;

	public GameMain(GameFrame gameFrame) {
		this.gameFrame=gameFrame;
		this.newGame();
	}

	
	
	
	public void newGame() {
		gameFrame.removeAll();
		this.level=new Level("/home/cullen/Projects/CSSE220/bubbleBobble/arcade-game-arcade-28/levels/level0/level0");
		gameFrame.add(level,BorderLayout.CENTER);
		gameFrame.repaint();
	}
	
	
	
	
	
	
	
	
	
	
	
}
