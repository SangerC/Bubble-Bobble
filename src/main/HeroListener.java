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
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
		case(27)://esc
			this.gameMain.togglePause();
			break;
		case(37)://left arrow
			this.hero.setKeyPressed(37);
			break;
		case(39)://right arrow
			this.hero.setKeyPressed(39);
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
	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){
			case(37)://left arrow
				if(this.hero.getKeyPressed()==37){
					this.hero.setKeyPressed(0);
				}
				break;
		case(39)://right arrow
			if(this.hero.getKeyPressed()==39){
				this.hero.setKeyPressed(0);
			}
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0){
		
	}

}
