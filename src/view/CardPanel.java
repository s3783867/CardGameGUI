package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.PlayingCardImpl;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;

@SuppressWarnings("serial")
public class CardPanel  extends JPanel{
	private HashMap<String, Vector<PlayingCard>> playerCardStorage = new HashMap<String, Vector<PlayingCard>>();
	private Vector<PlayingCard> houseCardStorage = new Vector<PlayingCard>();
	private boolean newRound = false;
	public CardPanel() {
		setLayout(new GridLayout(1,6));
		setBackground(Color.WHITE);
	}
	
	public void addCard(Player player, PlayingCard card) {
		Vector<PlayingCard> playersCards = playerCardStorage.get(player.getPlayerId());
		if(playersCards == null) {
			playerCardStorage.put(player.getPlayerId(), new Vector<PlayingCard>());
			playersCards = playerCardStorage.get(player.getPlayerId());
			playersCards.add(card);
			showCards(playersCards);
		} else {
			playersCards.add(card);
			showCards(playersCards);
		}
	}
	
	public void addHouseCard(PlayingCard card) {
		houseCardStorage.add(card);
		showCards(houseCardStorage);
	}
	
	public void showSelectedCards(String playerID) {
		if(playerID.equals("1")){
			if(houseCardStorage.size() > 0) {
				showCards(houseCardStorage);
			}
		} else { //displaying selected players cards
			Vector<PlayingCard> playersCards = playerCardStorage.get(playerID);
			if(playersCards != null) {
				showCards(playersCards);
				}
			}
	}
	
	public void showCards(Vector<PlayingCard> cards) {
		removeAll();
		int gridCount = 0;
		for(PlayingCard card : cards) {
			//add(new JLabel(card.getSuit().toString() + " " + card.getValue().toString()));
			add(new CardComponent(card));
			gridCount ++;
		}
		
		int gridRemaining = 6 - gridCount;
		if(gridRemaining > 0) {
			for(int i = 0; i < gridRemaining; i++) {
				add(new JLabel(""));
			}
		}
	}
	
	public boolean getNewRound() {
		return this.newRound;
	}
	
	public void setNewRound(boolean set) {
		this.newRound = set;
	}
	
	public void clearCardStorage() {
		playerCardStorage.clear();
		houseCardStorage.clear();
	}
}
