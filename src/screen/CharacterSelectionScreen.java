package screen;

import java.awt.BorderLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import animations.Animation;
import main.GameMain;

public class CharacterSelectionScreen extends JPanel {

	
	private Color backgroundColor = Color.DARK_GRAY;
	private GameFrame gameFrame;
	private GridBagConstraints c;
	private String characterinput;
	private ArrayList<Animation> hero;
			
	public CharacterSelectionScreen(GameFrame gameFrame){
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
		JButton selectButton = new JButton("Select Character and Begin");
		gameFrame.add(selectButton, BorderLayout.SOUTH);
		Animation mainanimation= this.hero.get(0);
		Animation previousanimation;
		Animation nextanimation= this.hero.get(1);
		
		selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//GameMain gameMain = new GameMain(gameFrame, herofolder);
			}		
		});
		c.gridx = 0;
		c.gridy = 1;
		this.add(selectButton,c);
		

	}
	
	private void makeTitle() {
		JLabel title = new JLabel("Character Selection");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("TimesRoman",Font.BOLD,45));
		title.setForeground(Color.red);
		title.setBackground(this.backgroundColor);
		title.setOpaque(true);
		gameFrame.add(title,BorderLayout.NORTH);
	}
	public String getCharacterType() {
		return this.characterinput;
	}
}
