package model;

import model.interfaces.PlayingCard;

/**
 * Assignment class for FP implementing PlayingCard interface and providing PlayCard functionality to the assignment.
 * 
 * @author Sovatharo Huy
 * 
 */

public class PlayingCardImpl implements PlayingCard {
	
	private PlayingCard.Suit suit;
	private PlayingCard.Value value;
	
	public PlayingCardImpl(PlayingCard.Suit suit, PlayingCard.Value value) {
		this.suit = suit;
		this.value = value;
	}

	@Override
	public Suit getSuit() {
		return this.suit;
	}

	@Override
	public Value getValue() {
		return this.value;
	}

	@Override
	public int getScore() {
		switch(this.getValue()) {
		case EIGHT: return 8;
		case NINE: return 9;
		case TEN: return 10;
		case JACK: return 10;
		case QUEEN: return 10;
		case KING: return 10;
		case ACE: return 11;
		default: return 0;
		}
	}
	//TODO FIX EQUALS AND HASHCODE implementation.
	@Override
	public boolean equals(PlayingCard card) {
		if(this.getSuit().equals(card.getSuit()) && this.getValue().equals(card.getValue())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	@Override
	public String toString() {
		String suit = this.getSuit().toString();
		String suitMod = suit.substring(0,1).toUpperCase() + suit.substring(1).toLowerCase();
		
		String value = this.getValue().toString();
		String valueMod = value.substring(0,1).toUpperCase() + value.substring(1).toLowerCase();
		//Suit: Clubs, Value: Five, Score: 5" for Five of Clubs
		return String.format(
				"Suit: %s, Value: %s, Score: %s",
				suitMod, valueMod, this.getScore());
	}
}
