package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.AddPlayerSubmitController;
import controller.CancelController;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class AddPlayerDialog extends JDialog{
	
	private GameEngine engine = new GameEngineImpl();
	private JLabel label1 = new JLabel("ID:");
	private JLabel label2 = new JLabel("Name");
	private JLabel label3 = new JLabel("Initial Points");
	
	private JTextField idField = new JTextField();
	private JTextField nameField = new JTextField();
	private JTextField pointsField = new JTextField();
	
	public AddPlayerDialog(GameEngine engine) {
		this.engine = engine;
		
		JButton okButton = new JButton("Submit");
		JButton cancelButton = new JButton("Cancel");
		setTitle("Add Player");
		setLayout(new GridLayout(4, 2));
		okButton.addActionListener(new AddPlayerSubmitController(this));
		cancelButton.addActionListener(new CancelController(this));
		add(label1);
		add(idField);
		add(label2);
		add(nameField);
		add(label3);
		add(pointsField);
		add(okButton);
		add(cancelButton);
	}
	
	public void generatePlayer() {
		if(idField.getText().equals("1")) {
			new InvalidInputDialog("ID can not be the same as house");
		} else if(nameField.getText().isEmpty() || idField.getText().isEmpty() || pointsField.getText().isEmpty()) {
			new InvalidInputDialog("Inputs must not be empty");
		} else if(!(isInt(pointsField.getText()))) {
			new InvalidInputDialog("Points must be an integer");
		} else if(Integer.parseInt(pointsField.getText()) < 0) {
			new InvalidInputDialog("Points must be more then 0");
		} else {
			Player generatedPlayer = new SimplePlayer(idField.getText(), nameField.getText(), Integer.parseInt(pointsField.getText()));
			this.engine.addPlayer(generatedPlayer);
		}
	}
	
	public void remove() {
		idField.setText(null);
		nameField.setText(null);
		pointsField.setText(null);
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
