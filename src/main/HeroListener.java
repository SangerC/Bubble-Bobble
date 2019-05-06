package main;

/**
 * The class that listens for the users controls in the arcade game.
 * 
 *Handles keys pressed and the operations that go with each of them
 * 
 *
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import drawables.Hero;

public class HeroListener implements KeyListener{

	private Hero hero;
	private GameMain gameMain;
	
	public HeroListener(Hero hero, GameMain gameMain){
		this.hero=hero;
		this.gameMain=gameMain;
		System.out.println("I'm alive");
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		System.out.println(e.getKeyCode());
		switch(e.getKeyCode()){
		case(27)://esc
			this.gameMain.pause();
			break;
		case(37)://left arrow
			this.hero.moveLeft();
			break;
		case(39)://right arrow
			this.hero.moveRight();
			break;
		case(38)://up arrow
			this.hero.jump();
			break;
		case(40)://down arrow
			break;
		case(85)://u
			this.gameMain.nextLevel();
			break;
		case(68)://d
			this.gameMain.previousLevel();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0){
		hero.setKeyPressed(0);
	}

	@Override
	public void keyTyped(KeyEvent arg0){
		
	}

}
