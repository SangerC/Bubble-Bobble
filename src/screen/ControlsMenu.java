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
import main.GameMain;

public class ControlsMenu extends JPanel{

	private Color backgroundColor = Color.DARK_GRAY;
	private GridBagConstraints c;

	
			
	public ControlsMenu(GameFrame gameFrame,JPanel previousPanel){
		super(new GridBagLayout());
		this.c = new GridBagConstraints();
		c.fill=GridBagConstraints.VERTICAL;
		JButton continueButton = new JButton("Back");
		continueButton.addActionListener(new BackListener(gameFrame,previousPanel,this));
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(40,0,0,0);
		this.add(continueButton,c);
		this.setPreferredSize(new Dimension(1280,720));
		this.setBackground(backgroundColor);
		gameFrame.remove(previousPanel);
		gameFrame.add(this);
		gameFrame.revalidate();
		gameFrame.repaint();
	}
	
	private class BackListener implements ActionListener{
			
			private JPanel previousPanel;
			private GameFrame gameFrame;
			private ControlsMenu controlsMenu;

			public BackListener(GameFrame gameFrame, JPanel previousPanel, ControlsMenu controlsMenu) {
				this.gameFrame=gameFrame;
				this.previousPanel=previousPanel;
				this.controlsMenu=controlsMenu;
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameFrame.remove(this.controlsMenu);
				gameFrame.add(this.previousPanel);
				gameFrame.revalidate();
				gameFrame.repaint();
				gameFrame.requestFocus();
			}		
		}
}
