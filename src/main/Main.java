package main;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import screen.GameFrame;
import screen.HomeScreen;



/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start
 * by running main here.
 * 
 * Also don't forget to write javadocs for your classes and methods.
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */
public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		GameFrame gameFrame = new GameFrame();
		JPanel homeScreen = new HomeScreen(gameFrame);
		gameFrame.add(homeScreen,BorderLayout.CENTER);
		gameFrame.revalidate();
		gameFrame.repaint();
	}

	
	
	
}
