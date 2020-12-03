package model;

import model.interfaces.Player;
/**
 * Assignment class for FP implementing Player interface and providing SimplePlayer functionality to the assignment.
 * 
 * @author Sovatharo Huy
 * 
 */
public class SimplePlayer implements Player {
	private String id;
	private String playerName;
	private int initialPoints;
	private int bet = 0;
	private int result = 0;
	
	public SimplePlayer(String id, String playerName, int initialPoints) {
		this.id = id;
		this.playerName = playerName;
		this.initialPoints = initialPoints;
	}
	
	@Override
	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		return this.initialPoints;
	}

	@Override
	public void setPoints(int points) {
		this.initialPoints = points;
	}

	@Override
	public String getPlayerId() {
		return this.id;
	}
	/**
	    * Sets the bet of a player
	    * @param bet
	    *            the bet in points
	    * @return true if the player had sufficient points and the bet was valid and placed or if the bet was set to 0.
	    */
	@Override
	public boolean setBet(int bet) {
		if(bet >= 1 && bet <= this.getPoints()) {
			this.bet = bet;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getBet() {
		return this.bet;
	}

	@Override
	public void resetBet() {
		this.bet = 0;
	}

	@Override
	public int getResult() {
		return this.result;
	}

	@Override
	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public boolean equals(Player player) {
		return this.getPlayerId().equals(player.getPlayerId());
	}
	//fixed in a2 for equals method obj
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Player)) 
			return false;
		return this == obj || equals((Player) obj);
	}

	@Override
	public int compareTo(Player player) {
		return this.getPlayerId().compareTo(player.getPlayerId());
	}
	
	@Override
	public int hashCode() {
		return this.getPlayerId().hashCode();
	}
	@Override
	public String toString() {
		return String.format(
				"Player: id=%s, name=%s, bet= %s, points=%s, RESULT .. %s",
				this.id, this.playerName, this.bet, this.initialPoints, this.result);
	}
}
