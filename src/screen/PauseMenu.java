package screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import listeners.ControlsListener;
import listeners.MainMenuListener;
import main.GameMain;

/**
 * Class that handles the pause menu layout and functions
 * 
 *
 */
public class PauseMenu extends JPanel{
	
	private Color backgroundColor = Color.DARK_GRAY;
	private GridBagConstraints c;
	private GameMain gameMain;
			
	public PauseMenu(GameMain gameMain){
		super(new GridBagLayout());
		this.c = new GridBagConstraints();
		this.gameMain = gameMain;
		c.fill=GridBagConstraints.VERTICAL;
		defaultButtonLayout();
		this.setPreferredSize(new Dimension(1280,720));
		this.setBackground(backgroundColor);
	}
	
	private void defaultButtonLayout(){
		JButton continueButton = new JButton("Continue");
		JButton controlsButton = new JButton("Controls");
		JButton mainMenuButton = new JButton("Main Menu");
		JButton quitButton = new JButton("Quit");
		continueButton.addActionListener(new PlayListener(this.gameMain));
		controlsButton.addActionListener(new ControlsListener(this.gameMain.getGameFrame(),this));
		mainMenuButton.addActionListener(new MainMenuListener(this.gameMain));
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}
		});
		c.gridx = 0;
		c.gridy = 1;
		this.add(continueButton,c);
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(40,0,0,0);
		this.add(controlsButton,c);
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(40,0,0,0);
		this.add(mainMenuButton,c);
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(40,0,0,0);
		this.add(quitButton,c);
	}
	
	private class PlayListener implements ActionListener{
			
			private GameMain gameMain;

			public PlayListener(GameMain gameMain) {
				this.gameMain=gameMain;
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				this.gameMain.togglePause();
			}		
	}
}
