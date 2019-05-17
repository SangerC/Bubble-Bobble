package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;

public class RepaintComponent implements ActionListener{

	JComponent component;
	
	public RepaintComponent(JComponent component){
		this.component=component;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.component.repaint();
	}

}
