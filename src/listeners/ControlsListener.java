package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import screen.ControlsMenu;
import screen.GameFrame;

/**
 * Class that handles when the control menu button is pressed
 * 
 */
public class ControlsListener implements ActionListener{
	
	private GameFrame gameFrame;
	private JPanel previousPanel;

	public ControlsListener(GameFrame gameFrame, JPanel previousPanel) {
		this.gameFrame=gameFrame;
		this.previousPanel=previousPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ControlsMenu controlMenu = new ControlsMenu(gameFrame,previousPanel);
	}		
}
