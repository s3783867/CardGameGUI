package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import controller.DealPlayerController;
import controller.DialogBoxController;
import controller.RemovePlayerController;
import controller.ResetBetController;
import model.GameEngineImpl;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class OptionsToolBar extends JToolBar{
	
	private GameEngine engine = new GameEngineImpl();
	private AddPlayerDialog addPlayerDialog;
	private PlayerSelectionBox playerSelectionBox;
	private SummaryPanel summaryPanel;
	private JButton dealPlayerButton = new JButton("Deal");
	private CardPanel cardPanel;
	public OptionsToolBar(GameEngine engine, AddPlayerDialog addPlayerDialog, PlayerSelectionBox playerSelectionBox, SummaryPanel summaryPanel, CardPanel cardPanel) {
		this.engine = engine;
		this.addPlayerDialog = addPlayerDialog;
		this.playerSelectionBox = playerSelectionBox;
		this.summaryPanel = summaryPanel;
		this.cardPanel = cardPanel;
		Border border = BorderFactory.createTitledBorder("Game Tool Bar");
		setBorder(border);
		
		dealPlayerButton.addActionListener(new DealPlayerController(this.engine, this.playerSelectionBox, this.cardPanel));
		
		JButton addPlayerButton = new JButton("Add Player");
		addPlayerButton.addActionListener(new DialogBoxController(this.addPlayerDialog));
		
		JButton setBetButton = new JButton("Bet");
		setBetButton.addActionListener(new DialogBoxController(new SetBetDialog(this.engine, this.playerSelectionBox, this.addPlayerDialog)));
		
		JButton resetBetButton = new JButton("Reset Bet");
		resetBetButton.addActionListener(new ResetBetController(this.playerSelectionBox, this.addPlayerDialog));
		
		JButton removePlayerButton = new JButton("Remove Player");
		removePlayerButton.addActionListener(new RemovePlayerController(this.playerSelectionBox, this.summaryPanel));
		
		add(dealPlayerButton);
		add(setBetButton);
		add(resetBetButton);
		add(this.playerSelectionBox);
		add(addPlayerButton);
		add(removePlayerButton);
	}
	
	public void toggleDealButton(boolean set) {
		dealPlayerButton.setEnabled(set);
	}
}
