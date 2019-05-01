package screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import main.GameMain;
import main.Main;

public class HomeScreen extends JPanel{
	
	private ArrayList<JButton> buttons;
	private Color backgroundColor = Color.DARK_GRAY;
	private GameFrame gameFrame;
			
	public HomeScreen(GameFrame gameFrame){
		this.gameFrame=gameFrame;
		this.buttons = new ArrayList<JButton>();
		defaultButtonLayout();
		this.setPreferredSize(new Dimension(1280,720));
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
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(backgroundColor);
		g.fillRect(0,0,1280,720);
		for(JButton b: buttons) {
			this.add(b,BorderLayout.CENTER);
		}
	}	

}
