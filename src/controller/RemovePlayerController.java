package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PlayerSelectionBox;
import view.SummaryPanel;

public class RemovePlayerController implements ActionListener{

	private PlayerSelectionBox playerSelectionBox;
	private SummaryPanel summaryPanel;
	
	public RemovePlayerController(PlayerSelectionBox playerSelectionBox, SummaryPanel summaryPanel) {
		this.playerSelectionBox = playerSelectionBox;
		this.summaryPanel = summaryPanel;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		playerSelectionBox.removePlayer();
		summaryPanel.updateData();
	}

}
