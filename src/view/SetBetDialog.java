package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.CancelController;
import controller.SetBetSubmitController;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class SetBetDialog extends JDialog{

	private GameEngine engine = new GameEngineImpl();
	private PlayerSelectionBox playerSelectionBox;
	private AddPlayerDialog addPlayerDialog;
	private JLabel label = new JLabel("Bet:");
	private JTextField betField = new JTextField();
	
	public SetBetDialog(GameEngine engine, PlayerSelectionBox playerSelectionBox, AddPlayerDialog addPlayerDialog) {
		this.engine = engine;
		this.playerSelectionBox = playerSelectionBox;
		this.addPlayerDialog = addPlayerDialog;
		
		JButton okButton = new JButton("Submit");
		JButton cancelButton = new JButton("Cancel");
		
		okButton.addActionListener(new SetBetSubmitController(this, this.addPlayerDialog));
		cancelButton.addActionListener(new CancelController(this));
		
		setTitle("Set Bet");
		setLayout(new GridLayout(2, 2));
		add(label);
		add(betField);
		add(okButton);
		add(cancelButton);
	}
	public void setBet() {
		Player selectedPlayer = playerSelectionBox.getSelectedPlayer();
		if(engine.getAllPlayers().size() <= 0) {
			new InvalidInputDialog("No players to set bet. Please add players");
		} else if(selectedPlayer.getPlayerId().equals("1")) {
			new InvalidInputDialog("Can not place bet for house");
		} else if(!(isInt(betField.getText()))) {
			new InvalidInputDialog("Bet must be an integer");
		} else if(Integer.parseInt(betField.getText()) < 0){
			new InvalidInputDialog("Bet must be more then 0");
		} else if(Integer.parseInt(betField.getText()) > engine.getPlayer(selectedPlayer.getPlayerId()).getPoints()) {
			new InvalidInputDialog(selectedPlayer.getPlayerName() + " does not have enough points");
		}
		else {
			engine.getPlayer(selectedPlayer.getPlayerId()).setBet(Integer.parseInt(betField.getText()));
		}
	}
	public void remove() {
		label.setText(null);
	}
	// notify listeners (the StatusBar)
	public void firePropertyChange(){
		 firePropertyChange("NEW_PLAYER", "oldValue", "newValue");
	}
	private boolean isInt(String string) {
		if (string == null) {
	        return false;
	    }
	    int length = string.length();
	    if (length == 0) {
	        return false;
	    }
	    int i = 0;
	    if (string.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = string.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}
}
