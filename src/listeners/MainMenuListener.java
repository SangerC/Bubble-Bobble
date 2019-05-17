package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.GameMain;

public class MainMenuListener implements ActionListener{
	
	private GameMain gameMain;

	public MainMenuListener(GameMain gameMain) {
		this.gameMain=gameMain;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.gameMain.stop();
	}	
}
