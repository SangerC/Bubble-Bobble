package screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import animations.Sprite;
import listeners.RepaintComponent;
import main.GameMain;

/**
 * Class that handles the character selection screen layout and functions
 *
 */
public class CharacterSelectionScreen extends JPanel {

	private static final int REPAINTDELAY = 16;
	
	private Color backgroundColor = Color.DARK_GRAY;
	private GameFrame gameFrame;
	private GameMain gameMain;
	private ArrayList<Sprite> hero;
	private Timer repaintTimer;
			
	public CharacterSelectionScreen(GameFrame gameFrame){
		this.gameFrame=gameFrame;
		this.hero=new ArrayList<Sprite>();
		this.hero.add(new Sprite("assests/heroes/adventurer",this,100,100));
		this.hero.add(new Sprite("assests/heroes/archer",this,100,100));
		this.setPreferredSize(new Dimension(1280,720));
		this.gameFrame.getContentPane().removeAll();
		defaultButtonLayout();
		this.repaintTimer=new Timer(REPAINTDELAY,new RepaintComponent(this));
		this.repaintTimer.restart();
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
		selectButton.addActionListener(new SelectListener(this));
		this.repaint();
		this.revalidate();
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
			screen.start();
		}
		
	}
	private class CharacterSelectionListener implements KeyListener{
		
		private CharacterSelectionScreen screen;
		
		public CharacterSelectionListener(CharacterSelectionScreen screen){
			this.screen=screen;
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==10){
				screen.start();
			}
			screen.changeSelection();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
			
		}
		
	}
	public void changeSelection(){
		Sprite temp = this.hero.get(0);
		this.hero.set(0, this.hero.get(1));
		this.hero.set(1, temp);
		this.repaint();
		this.revalidate();
	}
	public void start(){
		this.gameMain = new GameMain(gameFrame, this.getCharacterSelection());
		this.gameMain.newGame(0);
	}
	
}
