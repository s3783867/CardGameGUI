package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AddPlayerDialog;
import view.SetBetDialog;

public class SetBetSubmitController implements ActionListener{
	
	private SetBetDialog setBetDialog;
	private AddPlayerDialog addPlayerDialog;
	
	public SetBetSubmitController(SetBetDialog setBetDialog, AddPlayerDialog addPlayerDialog) {
		this.setBetDialog = setBetDialog;
		this.addPlayerDialog = addPlayerDialog;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		setBetDialog.setBet();
		addPlayerDialog.firePropertyChange();
		setBetDialog.remove();
		setBetDialog.dispose();
	}
	
}
