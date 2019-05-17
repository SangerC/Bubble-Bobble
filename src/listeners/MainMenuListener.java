package listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import screen.GameFrame;
import screen.HomeScreen;

public class MainMenuListener implements ActionListener{
	
	private GameFrame gameFrame;

	public MainMenuListener(GameFrame gameFrame) {
		this.gameFrame=gameFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.gameFrame.getContentPane().removeAll();
		JPanel homeScreen = new HomeScreen(gameFrame);
		this.gameFrame.add(homeScreen,BorderLayout.CENTER);
		this.gameFrame.revalidate();
		this.gameFrame.repaint();
	}	
}
