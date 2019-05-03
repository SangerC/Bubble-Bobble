package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import drawables.Hero;

public class HeroListener implements KeyListener{

	private Hero hero;
	
	public HeroListener(Hero hero) {
		this.hero=hero;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0){
		hero.setKeyPressed(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0){
		hero.setKeyPressed(0);
	}

	@Override
	public void keyTyped(KeyEvent arg0){
		
	}

}
