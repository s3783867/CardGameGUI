package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

import view.interfaces.GameEngineCallback;

@SuppressWarnings("serial")
public class GameEngineCallbackGUI extends JFrame implements GameEngineCallback {
	
	private GameEngine engine = new GameEngineImpl();
	private SummaryPanel southPanel;
	private AddPlayerDialog addPlayerDialog;
	private PlayerSelectionBox playerSelectionBox;
	private SetBetDialog setBetDialog;
	private StatusBar statusBar;
	private CardPanel cardPanel;
	private OptionsToolBar northBar;
	public GameEngineCallbackGUI(GameEngine engine) {
		super("Card Game GUI"); 	
		this.engine = engine;
		engine.addGameEngineCallback(this);
		//engine.addGameEngineCallback(new GameEngineCallbackImpl());
		setBounds(100, 100, 640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.cardPanel = new CardPanel();
		this.addPlayerDialog = new AddPlayerDialog(this.engine);
		this.statusBar = new StatusBar();
		this.playerSelectionBox = new PlayerSelectionBox(this.engine, this.addPlayerDialog, this.cardPanel);
		this.setBetDialog = new SetBetDialog(this.engine, this.playerSelectionBox, this.addPlayerDialog);
		this.southPanel = new SummaryPanel(this.engine, this.addPlayerDialog, this.statusBar);
		this.northBar = new OptionsToolBar(this.engine, this.addPlayerDialog, this.playerSelectionBox, this.southPanel, this.cardPanel);
		add(northBar, BorderLayout.NORTH);
		add(cardPanel, BorderLayout.CENTER);
		add(this.southPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		northBar.toggleDealButton(false);
		if(cardPanel.getNewRound()) {
			cardPanel.clearCardStorage();
			southPanel.setHouseResult(0);
			for(Player player2 : engine.getAllPlayers()) {
				player2.setResult(0);
			}
			this.southPanel.updateData();
			cardPanel.setNewRound(false);
		}
		this.cardPanel.addCard(player, card);
		this.statusBar.updateData(player.toString());
		this.southPanel.updateData();
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		this.cardPanel.addCard(player, card);
		this.statusBar.updateData(player.toString() + "...BUSTED!");
		this.southPanel.updateData();
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		this.statusBar.updateData(player.toString());
		this.southPanel.updateData();
		boolean allDealt = true;
		for(Player players : engine.getAllPlayers()) {
			if(players.getResult() == 0) {
				allDealt = false;
			}
		}
		if(allDealt) {
			engine.dealHouse(100);
		}
		northBar.toggleDealButton(true);
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		cardPanel.addHouseCard(card);
		statusBar.updateData(String.format("Card Dealt to House .. %s", card.toString()));
		southPanel.updateData();
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		cardPanel.addHouseCard(card);
		statusBar.updateData(String.format("Card Dealt to House .. %s ... HOUSE BUSTED!", card.toString()));
		southPanel.updateData();
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		southPanel.setHouseResult(result);
		//resetting bets for all players after round end.
		for(Player player : engine.getAllPlayers()) {
			player.resetBet();
		}
		//removes the player from the game if their points are 0
		for(Player player : engine.getAllPlayers()) {
			if(player.getPoints() == 0) {
				engine.removePlayer(player);
			}
		}
		
		//TODO display win/loss for each player
		this.statusBar.updateData("House result = " + result + " Win/Loss has been calculated");
		this.southPanel.updateData();
		this.playerSelectionBox.updateData();
		cardPanel.setNewRound(true);
	}

}
