package main;

/**
 * The GameMain class for the arcade game.
 * 
 * Handles game functions and uses the main GameFrame for the game
 * 
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Timer;
import screen.GameFrame;
import screen.Level;
import utility.Utilities;

public class GameMain {
	
	private static final int DELAY = 16;
	private static final String levelDirectory = "levels/";
	private static final int frameXSize = 1280;
	private static final int frameYSize = 720;
	private GameFrame gameFrame;
	private Level level;
	private HeroListener heroListener;
	private int currentLevel;
	private boolean paused=false;
	private Timer timer;

	public GameMain(GameFrame gameFrame) {
		this.gameFrame=gameFrame;
		this.newGame(0);
	}

	public void newGame(int levelNumber) {
		this.gameFrame.getContentPane().removeAll();
		this.level=new Level(levelDirectory+"level"+levelNumber+"/level"+levelNumber);
		this.level.setPreferredSize(new Dimension(1280,720));
		this.gameFrame.add(this.level,BorderLayout.CENTER);
		this.currentLevel=levelNumber;
		this.gameFrame.revalidate();
		this.gameFrame.repaint();
		this.heroListener=new HeroListener(level.getHero(),this);
		this.gameFrame.addKeyListener(this.heroListener);
		this.gameFrame.requestFocus();
		run();
	}
	
	private void run(){
		this.timer = new Timer(DELAY, new GameListener(this));
		this.timer.start();
	}
	
	public void nextLevel() {
		if(this.currentLevel!=Utilities.getNumberOfLevels(levelDirectory)){
			this.currentLevel++;
			this.endGame();
			newGame(this.currentLevel);
		}
	}
	
	public void previousLevel() {
		if(this.currentLevel!=0){
			this.currentLevel--;
			this.endGame();
			newGame(this.currentLevel);
		}
	}
	
	public void endGame(){
		this.gameFrame.removeKeyListener(this.heroListener);
		this.timer.stop();
	}
	
	public void togglePause() {
		if(this.paused){
			this.paused=false;
			this.timer.restart();
		}
		else {
			this.paused=true;
			this.timer.stop();
		}
	}

	public void checkCollisons() {
		this.level.checkCollisons();
	}
	
	public void update() {
		this.level.update();
	}	
	
	public void draw() {
		this.level.repaint();
	}
	
	public class GameListener implements ActionListener{
		
		private GameMain gameMain;
		public GameListener(GameMain gameMain) {
			this.gameMain=gameMain;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.gameMain.checkCollisons();
			this.gameMain.update();
			this.gameMain.draw();
		}	
	}
}
