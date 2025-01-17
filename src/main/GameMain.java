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
import javax.swing.JPanel;
import javax.swing.Timer;
import screen.EndScreen;
import screen.GameFrame;
import screen.HomeScreen;
import screen.Level;
import screen.PauseMenu;

public class GameMain {
	
	private static final int DELAY = 16;
	private static final String levelDirectory = "levels/";
	private static final int FRAMEXSIZE = 1280;
	private static final int FRAMEYSIZE = 720;
	private static final int STARTINGLIVES = 5;
	
	private GameFrame gameFrame;
	private Level level;
	private HeroListener heroListener;
	private int currentLevel;
	private boolean paused;
	private Timer timer;
	private int lives;
	private String heroFolder;
	private int score;

	public GameMain(GameFrame gameFrame, String heroFolder) {
		this.gameFrame=gameFrame;
		this.lives=STARTINGLIVES;
		this.heroFolder=heroFolder;
		this.paused=false;
		this.score=0;
	}

	public void newGame(int levelNumber) {
		this.gameFrame.getContentPane().removeAll();
		this.level=new Level(levelDirectory+"level"+levelNumber+"/level"+levelNumber,heroFolder,this);
		this.level.setPreferredSize(new Dimension(FRAMEXSIZE,FRAMEYSIZE));
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
		if(this.currentLevel!=getNumberOfLevels(levelDirectory)){
			this.currentLevel++;
			this.endGame();
			newGame(this.currentLevel);
		}
		else{
			EndScreen endScreen = new EndScreen(this,this.level.getHero().getSprite(),this.score);
		}
	}
	
	public static int getNumberOfLevels(String dirPath){
	    File f = new File(dirPath);
	    File[] files = f.listFiles();
	    return (files.length-2);
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
			this.gameFrame.getContentPane().removeAll();
			this.gameFrame.add(this.level);
			this.gameFrame.revalidate();
			this.gameFrame.repaint();
			this.gameFrame.requestFocus();
			this.timer.restart();
		}
		else{
			this.paused=true;
			this.gameFrame.getContentPane().removeAll();
			this.gameFrame.add(new PauseMenu(this));
			this.gameFrame.revalidate();
			this.gameFrame.repaint();
			this.timer.stop();
		}
	}

	public void checkCollisons() {
		this.level.checkCollisons();
	}
	
	public void update() {
		this.level.update();
		this.score+=this.level.getHero().getScore();
		this.level.getHero().setScore(0);
		if(this.level.getEnemies().size()==0&&this.level.getFruits().size()==0){
			this.nextLevel();
		}
		if(this.level.getHero().getDie()){
			this.level.getHero().setCanAct(true);
			this.level.reset();
			this.lives--;
			this.level.getHero().setDie(false);
			if(this.lives==0){
				this.stop();
			}
		}
	}	
	
	public void draw() {
		this.level.repaint();
	}
	
	public class GameListener implements ActionListener{
		
		private GameMain gameMain;
		public GameListener(GameMain gameMain){
			this.gameMain=gameMain;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0){
			this.gameMain.checkCollisons();
			this.gameMain.update();
			this.gameMain.draw();
		}	
	}

	public Level getLevel(){
		return this.level;
	}
	
	public int getCurrentLevel(){
		return this.currentLevel;
	}

	public int getLives(){
		return this.lives;
	}
	
	public boolean getPaused(){
		return this.paused;
	}

	public GameFrame getGameFrame(){
		return this.gameFrame;
	}

	public int getScore() {
		return this.score;
	}

	public void stop() {
		this.gameFrame.dispose();
		GameFrame gameFrame = new GameFrame();
		gameFrame.getContentPane().removeAll();
		JPanel homeScreen = new HomeScreen(gameFrame);
		gameFrame.add(homeScreen,BorderLayout.CENTER);
		gameFrame.revalidate();
		gameFrame.repaint();
		this.timer.stop();
	}
}
