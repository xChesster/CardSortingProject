/**************************************************************************
 * @author <Paul Lanham>
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: BoardSwitch.java
 *
 * Description: <This class uses a circularly linked list to keep track>
 * <of the players in the game. Keeps track of the deck of cards, the current player, and the number of players>
 ***************************************************************************/
public class BoardSwitch<T extends Card> extends Board<T>{
	
	// TO DO: add your implementation and JavaDoc
	
	public BoardSwitch(Deck<T> deck){
		//constructor
		//start with zero players
      super(deck);
      this.currentPlayer = null;
      this.numPlayer = 0;
	}

	@Override
	public Player<T> getCurrentPlayer() {
		// return the current player
		// O(1)
      return this.currentPlayer;
	}

	@Override
	public int getNumPlayers() {
		// return how many players 
		// O(1)
      return this.numPlayer;
	}
	
	@Override
	public Deck<T> getDeck(){
		//return the current deck
		// O(1)
      return this.deck;
	}

	@Override
	public boolean changeTurn() {
		// move the current player to the next one in the linked list
		// return false if cannot change
		// O(1)
		if(this.getCurrentPlayer().hasNext()){
         this.currentPlayer = this.getCurrentPlayer().getNext();
         return true;
      }
      return false;
	}
	
	@Override
	public void addPlayer(Player<T> x) {
		// add another player in the linked list
		// should add to the left of currentPlayer
		// O(N)
      
      //If there are no players, x's next will point to itself
      //and will be set as the current player 
      if(this.getNumPlayers() == 0){
         x.setNext(x);
         this.currentPlayer = x;
      }
      
      //If there is 1 player, x's next will point to the current
      //player and the current player will point to x, creating a circularly linked list
      else if(this.getNumPlayers() == 1){
         x.setNext(this.getCurrentPlayer());
         this.getCurrentPlayer().setNext(x);
      }
      
      //If there is more than 1 player
      else{
      
         //The new player's (x) next will point to the current player, connecting it to the list
         x.setNext(this.currentPlayer);
         //walker variable is used to traverse the linked list
         Player<T> walker = this.currentPlayer;
         
         //loops until reaching the player that comes before the current player in the list
         //and then its next is set to point to x which points to the current player
         while(walker.getNext() != this.currentPlayer){
      
            walker = walker.getNext();
         }
         
         walker.setNext(x);
      }
      // incrementing numPlayers by 1 since new player is added
      this.numPlayer++;
	}
	
	public Player<T> findWinner(){
		// return the player with the highest point
		// O(N)
      
      //highscore variable to keep track of the highest amount of total points recieved by a player
      int highScore = this.currentPlayer.getPoints();
      //walker variable used to traverse the linked list
		Player<T> walker = this.currentPlayer;
      //winner variable created to keep track of the player who has the highest highscore
      Player<T> winner = this.currentPlayer;
      
      //loops starts at the current player's position in the list and loops to the position
      //before the current player's position, completing a full loop of the list
      while(walker.getNext() != this.currentPlayer){
      
         walker = walker.getNext();
         //checks if a player has a higher score than highScore
         //and if so, the highScore variable is updated and the winner variable is set to
         //walker
         if(highScore < walker.getPoints()){
            highScore = walker.getPoints();
            winner = walker;
         }
         // comparing names of highscore ties to determine
         // winner by comparing names lexicographically
         else if(highScore == walker.getPoints()){
            
            if(winner.getName().compareTo(walker.getName()) > 0){
               winner = walker;
            }
         }
         
      }
      //player with the highest amount of points is returned
      return winner;
	}

	//-----------------------------------------------------
	// example test code... edit this as much as you want!
	// you will need working CardSwitch, Hand, Player, Deck and PlaySwitch classes to run the given code
	
	public static void main(String[] args) {
		Deck<CardSwitch> deck = new Deck<CardSwitch>();
		PlaySwitch.init_deck(deck);
			
		BoardSwitch<CardSwitch> myBoard = new BoardSwitch<CardSwitch>(deck);
		Player<CardSwitch> player1 = new Player<CardSwitch>("Tom");
		Player<CardSwitch> player2 = new Player<CardSwitch>("Jerry");

		myBoard.addPlayer(player1);
		
		if (myBoard.getNumPlayers() ==1  && myBoard.getCurrentPlayer() == player1
			&& player1.getNext() == player1){
			System.out.println("Yay 1");
		}

		myBoard.addPlayer(player2);
      
		if (myBoard.getNumPlayers() ==2  && myBoard.getCurrentPlayer() == player1
			&& (myBoard.changeTurn()==true) && myBoard.getCurrentPlayer() == player2){
			System.out.println("Yay 2");
		}
		
		player1.receiveCard(new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES));
		player1.receiveCard(new CardSwitch(Card.Rank.JACK, Card.Suit.CLUBS));
		player2.receiveCard(new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS));
		player2.receiveCard(new CardSwitch(Card.Rank.THREE, Card.Suit.SPADES));

		if (player1.getNext() == player2 && player2.getNext() == player1
			&& myBoard.findWinner() == player2){
			System.out.println("Yay 3");
		}
		
	
	}
	

}
