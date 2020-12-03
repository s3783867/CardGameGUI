package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.CardPanel;
import view.InvalidInputDialog;
import view.PlayerSelectionBox;


public class DealPlayerController implements ActionListener{
	private GameEngine engine;
	private PlayerSelectionBox playerSelectionBox;
	private CardPanel cardPanel;
	public DealPlayerController(GameEngine engine, PlayerSelectionBox playerSelectionBox, CardPanel cardPanel) {
		this.engine = engine;
		this.playerSelectionBox = playerSelectionBox;
		this.cardPanel = cardPanel;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(playerSelectionBox.getSelectedPlayer().getPlayerId().equals("1")) {
			new InvalidInputDialog("Can't deal for house");
		} else if(engine.getPlayer(playerSelectionBox.getSelectedPlayer().getPlayerId()).getBet() == 0){
			new InvalidInputDialog("Bet must bet set");
		} else if(engine.getPlayer(playerSelectionBox.getSelectedPlayer().getPlayerId()).getResult() != 0 && !(cardPanel.getNewRound())) {
			new InvalidInputDialog("Player has already been dealt");
		} else{
			engine.getPlayer(playerSelectionBox.getSelectedPlayer().getPlayerId()).setResult(0);
			new Thread()
			 {
			 @Override
			 public void run()
			 {
				engine.dealPlayer(engine.getPlayer(playerSelectionBox.getSelectedPlayer().getPlayerId()), 100);
			 }
			 }.start();
		}
	}

}
