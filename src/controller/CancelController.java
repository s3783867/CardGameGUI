package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class CancelController implements ActionListener{
	//responsible for closing JDialog boxes. Re-used in many views that open a JDialogBox
	private JDialog jDialog;
	public CancelController(JDialog jDialog) {
		this.jDialog = jDialog;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		jDialog.dispose();
	}
}
