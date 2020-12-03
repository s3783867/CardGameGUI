package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import controller.CancelController;

@SuppressWarnings("serial")
public class InvalidInputDialog extends JDialog{
	
	public InvalidInputDialog(String message) {
		setTitle("Invalid Input");
		JLabel label1 = new JLabel(message);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new CancelController(this));
		setLayout(new GridLayout(2, 0));
		add(label1);
		add(okButton);
		makeVisible();
	}
	
	public void makeVisible() {
		setSize(250,120);
		//TODO set relative to JFrame
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
