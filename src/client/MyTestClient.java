package client;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import model.GameEngineImpl;
import model.PlayingCardImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.GameEngineCallbackImpl;

public class MyTestClient {
	public void testMultiplePlayers() {
		System.out.println("Begin Test Multiple Players");
		Player[] players = new Player[] { new SimplePlayer("1", "The Shark", 1000), new SimplePlayer(
		         "2", "The Loser", 500), new SimplePlayer("a", "Test", 1000), new SimplePlayer("b", "Test", 1000), new SimplePlayer("c", "Test2", 1000),
				new SimplePlayer("d", "Test", 1000), new SimplePlayer("e", "Test2", 1000), new SimplePlayer("f", "Test", 1000), new SimplePlayer("g", "Test2", 1000)
				,new SimplePlayer("h", "Test", 1000), new SimplePlayer("i", "Test2", 1000), new SimplePlayer("j", "Test", 1000), new SimplePlayer("k", "Test2", 1000)
				,new SimplePlayer("l", "Test", 1000), new SimplePlayer("m", "Test2", 1000), new SimplePlayer("n", "Test", 1000), new SimplePlayer("o", "Test2", 1000)};
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		
		for (Player player : players)
	      {
	         gameEngine.addPlayer(player);
	         gameEngine.placeBet(player, 100);
	         gameEngine.dealPlayer(player, 10);
	      }
		gameEngine.dealHouse(10);
	}
	
	public void testInvalidBet() {
		System.out.println("Begin Test Invalid Bet");
		Player[] players = new Player[] { new SimplePlayer("1", "The Shark", 1000), new SimplePlayer(
		         "2", "The Loser", 500)};
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		
		for (Player player : players)
	      {
	         gameEngine.addPlayer(player);
	         gameEngine.placeBet(player, -500);
	         gameEngine.dealPlayer(player, 100);
	      }
		gameEngine.dealHouse(10);
	}
	
	public void testMultipleGEC() {
		System.out.println("Begin Test Multiple GEC");
		Player[] players = new Player[] { new SimplePlayer("1", "The Shark", 1000), new SimplePlayer(
		         "2", "The Loser", 500)};
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		
		for (Player player : players)
	      {
	         gameEngine.addPlayer(player);
	         gameEngine.placeBet(player, 100);
	         gameEngine.dealPlayer(player, 100);
	      }
		gameEngine.dealHouse(10);
	}
	
	public void testRemovePlayer() {
		System.out.println("Begin Test Remove Player");
		Player[] players = new Player[] { new SimplePlayer("1", "The Shark", 1000), new SimplePlayer(
		         "2", "The Loser", 500)};
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		
		for (Player player : players)
	      {
	         gameEngine.addPlayer(player);
	         gameEngine.placeBet(player, 100);
	         gameEngine.dealPlayer(player, 100);
	      }
		gameEngine.removePlayer(players[0]);
		gameEngine.dealHouse(10);
	}
	
	public void testIfResetBet() {
		System.out.println("Begin Test if bets are reset after round end");
		Player[] players = new Player[] { new SimplePlayer("1", "The Shark", 1000), new SimplePlayer(
		         "2", "The Loser", 500)};
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		
		for (Player player : players)
	      {
	         gameEngine.addPlayer(player);
	         gameEngine.placeBet(player, 100);
	         gameEngine.dealPlayer(player, 100);
	      }
		gameEngine.dealHouse(10);
		
		System.out.println(gameEngine.getAllPlayers());
	}
	
	public void testAddSamePlayerId() {	
		System.out.println("Begin Test of adding same player id");
		Player[] players = new Player[] { new SimplePlayer("1", "The Shark", 1000), new SimplePlayer(
		         "1", "The Loser", 500)};
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		
		for (Player player : players)
	      {
	         gameEngine.addPlayer(player);
	      }
		System.out.println(gameEngine.getAllPlayers());
	}
	
	public void testMultipleRounds() {
		System.out.println("Begin Test multiple rounds");
		Player[] players = new Player[] { new SimplePlayer("1", "The Shark", 1000), new SimplePlayer(
		         "2", "The Loser", 500)};
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		
		for (Player player : players)
	      {
	         gameEngine.addPlayer(player);
	         gameEngine.placeBet(player, 100);
	         gameEngine.dealPlayer(player, 100);
	      }
		gameEngine.dealHouse(10);
		
		for (Player player : players)
	      {
	         gameEngine.placeBet(player, 100);
	         gameEngine.dealPlayer(player, 100);
	      }
		gameEngine.dealHouse(10);
	}
	
	public void testBetMoreThenPoints() {
		System.out.println("Begin Test if player bet more then player points");
		Player[] players = new Player[] { new SimplePlayer("1", "The Shark", 1000), new SimplePlayer(
		         "2", "The Loser", 500)};
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		
		for (Player player : players)
	      {
	         gameEngine.addPlayer(player);
	         gameEngine.placeBet(player, 600);
	         gameEngine.dealPlayer(player, 100);
	      }
		gameEngine.dealHouse(10);
	}
	
	public void testHalfDeckCreationMethod() {
		System.out.println("Begin test half deck creation.");
List<PlayingCard> shuffledHalfDeck = new LinkedList<PlayingCard>();
		
		for(PlayingCard.Suit suit : PlayingCard.Suit.values()) {
			for(PlayingCard.Value value : PlayingCard.Value.values()) {

				shuffledHalfDeck.add(new PlayingCardImpl(suit, value));
			}
		}
		
		System.out.println(shuffledHalfDeck);
		
		Collections.shuffle(shuffledHalfDeck);
		Deque<PlayingCard> shuffledHalfDeckLinked = new LinkedList<PlayingCard>();
		
		for(PlayingCard playingCard : shuffledHalfDeck) {
			shuffledHalfDeckLinked.add(playingCard );
		}
		System.out.println(shuffledHalfDeckLinked);
	}
}
