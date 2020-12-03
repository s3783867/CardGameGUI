package view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class StatusBar extends JLabel{

	public StatusBar() {
		Border border = BorderFactory.createTitledBorder("Game Status");
		setBorder(border);
		setText("Status");
	}
	
	public void updateData(String text) {
		setText(text);
	}
}
