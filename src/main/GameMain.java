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

public class GameMain {
	
	private static final int DELAY = 1;
	private static final String levelDirectory = "levels/";
	private static final int frameXSize = 1280;
	private static final int frameYSize = 720;
	private GameFrame gameFrame;
	private Level level;
	private HeroListener heroListener;
	private int currentLevel;
	private boolean running=false;
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
	
	public void update() {
		this.level.update();
	}	
	
	public void draw() {
		this.level.repaint();
	}
	
	public void nextLevel() {
		if(this.currentLevel==getNumberOfLevels(levelDirectory)){
			
		}
		else{
			this.currentLevel++;
			newGame(this.currentLevel);
		}
	}
	
	public void previousLevel() {
		if(this.currentLevel==0){
			
		}
		else{
			this.currentLevel--;
			newGame(this.currentLevel);
		}
	}
	
	private int getNumberOfLevels(String dirPath){
		    File f = new File(dirPath);
		    File[] files = f.listFiles();
		    return (files.length-2);
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

	public void pause() {
		this.paused=true;
		this.timer.stop();
	}

	public void checkCollisons() {
		this.level.checkCollisons();
	}
}
