package screen;

import java.awt.BorderLayout;

/**
 * The home screen class for the arcade game.
 * 
 * 
 * Creates the home screen which allows the user to begin playing the game
 *
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.GameMain;

/**
 * Class that handles the creation and layout of the home screen
 * 
 *
 */
public class HomeScreen extends JPanel{
	
	private Color backgroundColor = Color.DARK_GRAY;
	private GameFrame gameFrame;
	private GridBagConstraints c;
			
	public HomeScreen(GameFrame gameFrame){
		super(new GridBagLayout());
		this.c = new GridBagConstraints();
		c.fill=GridBagConstraints.VERTICAL;
		this.gameFrame=gameFrame;
		defaultButtonLayout();
		this.setPreferredSize(new Dimension(1280,720));
		this.setBackground(backgroundColor);
		makeTitle();
	}
	
	private void defaultButtonLayout(){
		JButton playButton = new JButton("Play");
		JButton controlsButton = new JButton("Controls");
		JButton quitButton = new JButton("Quit");
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CharacterSelectionScreen charselect = new CharacterSelectionScreen(gameFrame);
			}		
		});
		controlsButton.addActionListener(new ControlsListener(this.gameFrame,this));
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}
			
		});
		c.gridx = 0;
		c.gridy = 1;
		this.add(playButton,c);
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(40,0,0,0);
		this.add(controlsButton,c);
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(40,0,0,0);
		this.add(quitButton,c);
	}
	
	private void makeTitle() {
		JLabel title = new JLabel("Bubble Bobble");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("TimesRoman",Font.BOLD,45));
		title.setForeground(Color.red);
		title.setBackground(this.backgroundColor);
		title.setOpaque(true);
		gameFrame.add(title,BorderLayout.NORTH);
	}
}
