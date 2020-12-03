package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.AddPlayerDialog;
import view.PlayerSelectionBox;

public class ResetBetController implements ActionListener{
	
	private PlayerSelectionBox playerSelectionBox;
	private AddPlayerDialog addPlayerDialog;
	
	public ResetBetController(PlayerSelectionBox playerSelectionBox, AddPlayerDialog addPlayerDialog) {
		this.playerSelectionBox = playerSelectionBox;
		this.addPlayerDialog = addPlayerDialog;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.playerSelectionBox.resetBet();
		addPlayerDialog.firePropertyChange();
	}

}
