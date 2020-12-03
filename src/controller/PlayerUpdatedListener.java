package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import view.PlayerSelectionBox;
import view.SummaryPanel;

public class PlayerUpdatedListener implements PropertyChangeListener{
	
	private SummaryPanel summaryPanel;
	private PlayerSelectionBox playerSelectionBox;
	
	public PlayerUpdatedListener(SummaryPanel summaryPanel) {
		this.summaryPanel = summaryPanel;
	}
	public PlayerUpdatedListener(PlayerSelectionBox playerSelectionBox) {
		this.playerSelectionBox = playerSelectionBox;
	}
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if(summaryPanel != null) {
			summaryPanel.updateData();
		}
		if(playerSelectionBox != null) {
			playerSelectionBox.updateData();
		}
	}

}
