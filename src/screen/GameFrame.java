package screen;

/**
 * The frame class for the arcade game.
 * 
 * Handles the specifications of the frame that appears when the program runs
 * 
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;


public class GameFrame extends JFrame{

	public GameFrame() {
		super("Bubble Bobble");
		this.setSize(1280, 720);
		setResizable(false);
		this.setVisible(true);
		this.getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	
	
}
