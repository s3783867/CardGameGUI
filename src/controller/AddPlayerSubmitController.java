package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AddPlayerDialog;

public class AddPlayerSubmitController implements ActionListener{
	
	private AddPlayerDialog addPlayerDialog;
	
	public AddPlayerSubmitController(AddPlayerDialog addPlayerDialog) {
		this.addPlayerDialog = addPlayerDialog;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		addPlayerDialog.generatePlayer();
		addPlayerDialog.firePropertyChange();
		addPlayerDialog.remove();
		addPlayerDialog.dispose();
	}

}
