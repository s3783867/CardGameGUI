package view;

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import controller.PlayerUpdatedListener;
import controller.PlayerSelectionController;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class PlayerSelectionBox extends JComboBox<Player>{
	
	private Player selectedPlayer;
	private AddPlayerDialog addPlayerDialog;
	private GameEngine engine = new GameEngineImpl();
	private Vector<Player> data = new Vector<Player>();
	private DefaultComboBoxModel<Player> model = (DefaultComboBoxModel<Player>) getModel();
	private CardPanel cardPanel;
	public PlayerSelectionBox(GameEngine engine, AddPlayerDialog addPlayerDialog, CardPanel cardPanel) {
		
		this.engine = engine;
		this.addPlayerDialog = addPlayerDialog;
		this.cardPanel = cardPanel;
		addPlayerPropListener(new PlayerUpdatedListener(this));
		updateData();
		addItemListener(new PlayerSelectionController(this, this.cardPanel));
	}
	
	public void updateData() {
			removeAllItems();
			data.clear();
				Player house = new SimplePlayer("1", "House", 9999);
				data.add(house);
				model.addElement(house);
				if(engine.getAllPlayers().size() > 0) {
					for(Player player : engine.getAllPlayers()) {
						data.add(player);
					}
					for(Player player : data) {
						model.addElement(player);
					}
				}
				setModel(model);
				setVisible(true);
				setRenderer(new PlayerSelectionRenderer());
	}
	
	public void removePlayer() {
		if(selectedPlayer.getPlayerId().equals("1")) {
			new InvalidInputDialog("Can not remove the house");
		} else {
			engine.removePlayer(this.selectedPlayer);
			updateData();
		}
	}
	public void addPlayerPropListener(PlayerUpdatedListener addPlayerListener) {
		addPlayerDialog.addPropertyChangeListener("NEW_PLAYER", addPlayerListener);
	}
	
	public void setSelectedPlayer(Player player) {
		this.selectedPlayer = player;
	}
	
	public Player getSelectedPlayer() {
		return this.selectedPlayer;
	}
	
	public void resetBet() {
		if(selectedPlayer.getPlayerId().equals("1")) {
			new InvalidInputDialog("Can not reset bet for house");
		} else {
			engine.getPlayer(selectedPlayer.getPlayerId()).resetBet();
		}
	}
	
}
