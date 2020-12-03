package controller;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import model.GameEngineImpl;
import model.interfaces.GameEngine;

public class DialogBoxController implements ActionListener{
	//responsible for showing a JDialogBox.
	GameEngine engine = new GameEngineImpl();
	JDialog jDialog;
	
	public DialogBoxController(JDialog jDialog) {
		this.jDialog = jDialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		jDialog.setSize(250,120);
		jDialog.setLocationRelativeTo(null);
		jDialog.setModalityType(ModalityType.APPLICATION_MODAL);
		jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jDialog.setVisible(true);
	}
}
