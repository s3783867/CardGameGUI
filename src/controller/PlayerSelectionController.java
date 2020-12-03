package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.SwingUtilities;

import model.interfaces.Player;
import view.CardPanel;
import view.PlayerSelectionBox;

public class PlayerSelectionController implements ItemListener{
	
	private PlayerSelectionBox playerSelectionBox;
	private CardPanel cardPanel;
	public PlayerSelectionController(PlayerSelectionBox playerSelectionBox, CardPanel cardPanel) {
		this.playerSelectionBox = playerSelectionBox;
		this.cardPanel = cardPanel;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		playerSelectionBox.setSelectedPlayer((Player)e.getItem());
		cardPanel.showSelectedCards(playerSelectionBox.getSelectedPlayer().getPlayerId());
	}

}
