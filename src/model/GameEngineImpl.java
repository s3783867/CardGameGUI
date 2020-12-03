package model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine{
	private HashMap<String, Player> players = new HashMap<String, Player>();
	private Deque<PlayingCard> halfDeck = new LinkedList<PlayingCard>();
	private Collection<GameEngineCallback> gameEngineCall = new ArrayList<GameEngineCallback>();
	private Player house = new SimplePlayer("0", "House", 9999);
	
	public GameEngineImpl() {
		this.halfDeck = getShuffledHalfDeck();
	}
	/**
	    * This method handles dealing a card to a player.
	    * 
	    * 1. Check if delay is valid if invalid throw exception.
	    * 2. Fill the deck if it is empty
	    * 2. Loop through dealing a card to a player until player busts while calling nextCard in GEC.
	    * 3. When player bust call bustCard and resultCard in GEC
	    * @param player the current player who will have their result set at the end of the hand
	    * @param delay the delay between cards being dealt (in milliseconds (ms))
	    *            
	    * @throws IllegalArgumentException thrown when delay param is 0 or 1000
	    */
	@Override
	public void dealPlayer(Player player, int delay) throws IllegalArgumentException {
		if(delay < 0 || delay > 1000) {
			throw new IllegalArgumentException();
		}
		boolean playerNotBust = true;
		do {
			if(halfDeck.size() <= 0) {
				halfDeck = getShuffledHalfDeck();
			}
			PlayingCard nextCard = halfDeck.pop();
			int result = player.getResult() + nextCard.getScore();
			if(result <= GameEngine.BUST_LEVEL) {
				for(GameEngineCallback eachGameEngine : gameEngineCall) {
					eachGameEngine.nextCard(player, nextCard, this);
				}
				player.setResult(result);
			} else {
				for(GameEngineCallback eachGameEngine : gameEngineCall) {
					eachGameEngine.bustCard(player, nextCard, this);
					eachGameEngine.result(player, player.getResult(), this);
				}
				playerNotBust = false;
			}
			delay(delay);
		} while(playerNotBust);

	}
	/**
	    * This method deals for the house and calls the  house versions of the callback methods on GEC.
	    * 
	    * 1. Check if delay is valid if invalid throw exception.
	    * 2. Fill the deck if it is empty
	    * 2. Loop through dealing a card to a house until house busts while calling nextHouseCard in GEC.
	    * 3. When player bust call houseBustCard and houseResult in GEC
	    * 4. Reset the bets of all players.
	    * @param delay the delay between cards being dealt (in milliseconds (ms))
	    *            
	    * @throws IllegalArgumentException thrown when delay param is < 0
	    */
	@Override
	public void dealHouse(int delay) throws IllegalArgumentException {
		if(delay < 0) {
			throw new IllegalArgumentException();
		}
		boolean houseNotBust = true;
		do {
			if(this.halfDeck.size() <= 0) {
				this.halfDeck = getShuffledHalfDeck();
			}
			PlayingCard nextCard = halfDeck.pop();
			int result = this.house.getResult() + nextCard.getScore();
			if(result <= GameEngine.BUST_LEVEL) {
				for(GameEngineCallback eachGameEngine : gameEngineCall) {
					eachGameEngine.nextHouseCard(nextCard, this);
				}
				this.house.setResult(result);
			} else {
				for(GameEngineCallback eachGameEngine : gameEngineCall) {
					Iterator<Entry<String, Player>> it = this.players.entrySet().iterator();
					while(it.hasNext()) {
						Map.Entry<String,Player> mapElement = (Entry<String, Player>)it.next();
						applyWinLoss(mapElement.getValue(), this.house.getResult());
					}
					eachGameEngine.houseBustCard(nextCard, this);
					eachGameEngine.houseResult(this.house.getResult(), this);
				}
				houseNotBust = false;
			}
			delay(delay);
		} while(houseNotBust);
		
		Iterator<Entry<String, Player>> it = this.players.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String,Player> mapElement = (Entry<String, Player>)it.next();
			mapElement.getValue().resetBet();
		}
		//added in A2 to fix house
		house.setResult(0);
	}
	
	private void delay(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	    * <pre>
	    * A player's bet is settled by this method 
	    * 1. Loop through for each player in the collection of players.
	    * 2. If player result is more then house result then player win.
	    * 3. If player result is less then house result then player lose.
	    * 4. If player result is equal to house result then it is draw.
	    * 
	    * @param player - the Player to apply win/loss to
	    * @param houseResult - contains the calculated house result
	    * </pre>
	    */
	@Override
	public void applyWinLoss(Player player, int houseResult) {
			int currentPoints = player.getPoints();
			if(player.getResult() > houseResult) {
				player.setPoints(currentPoints + player.getBet()); 
			} else if(player.getResult() < houseResult){
				player.setPoints(currentPoints - player.getBet()); 
			}
	}

	@Override
	public void addPlayer(Player player) {
		this.players.put(player.getPlayerId(), player);
	}

	@Override
	public Player getPlayer(String id) {
		return this.players.get(id);
	}

	@Override
	public boolean removePlayer(Player player) {
		Player removedPlayer = (Player)this.players.remove(player.getPlayerId());
		if(removedPlayer.equals(player)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		return player.setBet(bet);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCall.add(gameEngineCallback);
		
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return this.gameEngineCall.remove(gameEngineCallback);
	}
	/**
	    * <pre>
	    * Creates and returns an unmodifiable collection of all players sorted lexicographically.
	    * 1. Create a collection of players.
	    * 2. Create and fill an array of players with all current players.
	    * 3. Sort the array in lexicographic order using the compareTo method.
	    * 4. Fill the unmodifiable collection of players with the now sorted array of players.
	    * 5. Return the collection.
	    * @return an unmodifiable collection (or a shallow copy) of all Players
	    * Collection is SORTED in ascending order by player id</pre>
	    */
	@Override
	public Collection<Player> getAllPlayers() {
		Collection<Player> collectionPlayers = new HashSet<Player>();
		int index = 0;
		int arrSize = this.players.size();
		Player playerArr[] = new Player[arrSize];
		
		Iterator<Entry<String, Player>> it = this.players.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String,Player> mapElement = (Entry<String, Player>)it.next();
			playerArr[index] = (Player)mapElement.getValue();
			index++;
		}

		for(int i = 0; i < arrSize; i++) {
			for(int j = i + 1; j < arrSize; j++) {
				if(playerArr[i].compareTo(playerArr[j]) > 0) {
					Player temp = playerArr[i];
					playerArr[i] = playerArr[j];
					playerArr[j] = temp;
				}
			}
		}
		
		for(int i = 0; i < arrSize; i++) {
			collectionPlayers.add(playerArr[i]);
		}
		Collection<Player> unModCollectionPlayers = Collections.unmodifiableCollection(collectionPlayers);
		
		return unModCollectionPlayers;
		
	}
	/**
	    * <pre>
	    * Creation of a shuffled half deck is settled by this method 
	    * 1. Fill a half deck with all the cards.
	    * 2. Shuffle the half deck.
	    * 3. Clear the current half deck.
	    * 4. Fill a Deque<> halfDeck with the shuffled cards.
	    * 5. Return the shuffled half deck.
	    * </pre>
	    */
	@Override
	public Deque<PlayingCard> getShuffledHalfDeck() {
		List<PlayingCard> shuffledHalfDeck = new LinkedList<PlayingCard>();
		
		for(PlayingCard.Suit suit : PlayingCard.Suit.values()) {
			for(PlayingCard.Value value : PlayingCard.Value.values()) {

				shuffledHalfDeck.add(new PlayingCardImpl(suit, value));
			}
		}
		
		Collections.shuffle(shuffledHalfDeck);
		Deque<PlayingCard> shuffledHalfDeckLinked = new LinkedList<PlayingCard>();
		this.halfDeck.clear();
		
		for(PlayingCard playingCard : shuffledHalfDeck) {
			shuffledHalfDeckLinked.add(playingCard);
		}
		return shuffledHalfDeckLinked;
	}
}
