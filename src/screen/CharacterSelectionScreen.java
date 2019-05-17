package screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
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
			
	public CharacterSelectionScreen(GameFrame gameFrame){
		super(new GridBagLayout());
		this.c = new GridBagConstraints();
		c.fill=GridBagConstraints.VERTICAL;
		this.gameFrame=gameFrame;
		this.hero=new ArrayList<Sprite>();
		this.hero.add(new Sprite("assests/heroes/adventurer",this,100,100));
		this.hero.add(new Sprite("assests/heroes/archer",this,100,100));
		defaultButtonLayout();
		this.setPreferredSize(new Dimension(1280,720));
		this.gameFrame.getContentPane().removeAll();
		this.gameFrame.add(this);
		this.gameFrame.revalidate();
		this.gameFrame.repaint();
		this.gameFrame.requestFocus();
		this.gameFrame.addKeyListener(new CharacterSelectionListener(this));
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
		c.insets = new Insets(300,0,0,0);
		this.add(selectButton,c);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(1280/2-50, 720/2 -50);
		this.hero.get(0).draw(g2);
		g2.translate(150, 0);
		this.hero.get(1).draw(g2);
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
		return this.hero.get(0).getFolderName();
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
	private class CharacterSelectionListener implements KeyListener{
		
		private CharacterSelectionScreen screen;
		
		public CharacterSelectionListener(CharacterSelectionScreen screen){
			this.screen=screen;
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			screen.changeSelection();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public void changeSelection(){
		System.out.println("awdaw");
		Sprite temp = this.hero.get(0);
		this.hero.set(0, this.hero.get(1));
		this.hero.set(1, temp);
		this.repaint();
	}
	
}
