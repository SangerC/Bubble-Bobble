package screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
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
}
