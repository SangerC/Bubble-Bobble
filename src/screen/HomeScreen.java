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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.GameMain;


public class HomeScreen extends JPanel{
	
	private ArrayList<JButton> buttons;
	private Color backgroundColor = Color.DARK_GRAY;
	private GameFrame gameFrame;
	private GridBagConstraints c;
			
	public HomeScreen(GameFrame gameFrame){
		super(new GridBagLayout());
		this.c = new GridBagConstraints();
		this.gameFrame=gameFrame;
		this.buttons = new ArrayList<JButton>();
		defaultButtonLayout();
		this.setPreferredSize(new Dimension(1280,720));
		this.setBackground(backgroundColor);
		makeTitle();
	}
	
	private void defaultButtonLayout(){
		JButton playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameMain gameMain = new GameMain(gameFrame);
			}		
			
		});
		this.buttons.add(playButton);
		for(JButton b: buttons) {
			this.add(b,c);
		}
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
