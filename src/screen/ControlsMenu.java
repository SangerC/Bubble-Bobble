package screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Class that handles the control menu layout
 *
 */

public class ControlsMenu extends JPanel{

    public static final int BACKBUTTONVERTICALOFFSET = 500;
	
	private Color backgroundColor = Color.DARK_GRAY;
	private GridBagConstraints c;
	private Image controlsImage;
			
	public ControlsMenu(GameFrame gameFrame,JPanel previousPanel){
		super(new GridBagLayout());
		this.c = new GridBagConstraints();
		c.fill=GridBagConstraints.VERTICAL;
		JButton continueButton = new JButton("Back");
		continueButton.addActionListener(new BackListener(gameFrame,previousPanel,this));
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(BACKBUTTONVERTICALOFFSET,0,0,0);
		this.add(continueButton,c);
		this.setPreferredSize(new Dimension(1280,720));
		this.setBackground(backgroundColor);
		try {
			this.controlsImage = ImageIO.read(new File("assests/controls/controls.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(this.controlsImage!=null) {
			g2.drawImage(this.controlsImage, 1280/2-this.controlsImage.getWidth(this)/2, 720/2-this.controlsImage.getHeight(this),this);
		}
	}
}
