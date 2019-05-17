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
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import animations.Sprite;
import main.GameMain;

/**
 * Class that handles the character selection screen layout and functions
 *
 */
public class CharacterSelectionScreen extends JPanel {

	
	private Color backgroundColor = Color.DARK_GRAY;
	private GameFrame gameFrame;
	private GridBagConstraints c;
	private ArrayList<Sprite> hero;
	private int currentHeroIndex;
			
	public CharacterSelectionScreen(GameFrame gameFrame){
		super(new GridBagLayout());
		this.c = new GridBagConstraints();
		c.fill=GridBagConstraints.VERTICAL;
		this.gameFrame=gameFrame;
		this.hero=new ArrayList<Sprite>();
		this.currentHeroIndex=0;
		this.hero.add(new Sprite("assests/heroes/adventurer",this,100,100));
		this.hero.add(new Sprite("assests/heroes/adventurer",this,100,100));
		defaultButtonLayout();
		this.setPreferredSize(new Dimension(1280,720));
		this.gameFrame.getContentPane().removeAll();
		this.gameFrame.add(this);
		this.gameFrame.revalidate();
		this.gameFrame.repaint();
		this.setBackground(backgroundColor);
		makeTitle();
}
	private void defaultButtonLayout(){
		JButton selectButton = new JButton("Select Character and Begin");
		gameFrame.add(selectButton, BorderLayout.SOUTH);
		Sprite mainanimation= this.hero.get(0);
		Sprite previousanimation;
		Sprite nextanimation= this.hero.get(1);
		
		selectButton.addActionListener(new SelectListener(this));
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
	public String getCharacterSelection() {
		return this.hero.get(currentHeroIndex).getFolderName();
	}
	private class SelectListener implements ActionListener{
		
		private CharacterSelectionScreen screen;
		
		public SelectListener(CharacterSelectionScreen screen){
			this.screen=screen;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			GameMain gameMain = new GameMain(gameFrame, this.screen.getCharacterSelection());
		}
		
	}
}
