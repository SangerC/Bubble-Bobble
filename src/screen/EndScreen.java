package screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import animations.Sprite;
import listeners.MainMenuListener;
import listeners.RepaintComponent;

public class EndScreen extends JPanel{

	private static final int REPAINTDELAY = 16;
	
	private Color backgroundColor = Color.DARK_GRAY;
	private GameFrame gameFrame;
	private Sprite hero;
	private int score;
	private Timer repaintTimer;
			
	public EndScreen(GameFrame gameFrame,Sprite hero, int score){
		this.gameFrame=gameFrame;
		this.hero=hero;
		this.setPreferredSize(new Dimension(1280,720));
		this.gameFrame.getContentPane().removeAll();
		this.gameFrame.add(this);
		this.score=score;
		this.setBackground(backgroundColor);
		JButton selectButton = new JButton("Main Menu");
		gameFrame.add(selectButton, BorderLayout.SOUTH);
		selectButton.addActionListener(new MainMenuListener(gameFrame));
		this.repaintTimer=new Timer(REPAINTDELAY,new RepaintComponent(this));
		this.repaintTimer.restart();
		this.gameFrame.revalidate();
		this.gameFrame.repaint();
		this.gameFrame.requestFocus();
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("TimesRoman",Font.BOLD,45));
		g2.setColor(Color.red);
		drawString(g2,"   You Won\nYour Score: "+this.score, 500, 100);
		g2.translate(1280/2-50, 720/2 -50);
		this.hero.draw(g2);
		g2.translate(-(1280/2-50), -(720/2 -50));
	}
	void drawString(Graphics2D g, String text, int x, int y) {
	    for (String line : text.split("\n"))
	        g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}
}
