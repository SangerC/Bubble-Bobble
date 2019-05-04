package screen;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class GameFrame extends JFrame{

	public GameFrame() {
		super("Bubble Bobble");
		this.setSize(1280, 720);
		this.setVisible(true);
		this.getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	
	
}
