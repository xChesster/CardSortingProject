/**************************************************************************
 * @author <Paul Lanham>
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Player.java
 *
 * Description: <This class represents a player and keeps track of a player's name,>
 * <points, hand, and the player next in line. The class keeps track of the players with a linked list>
 ***************************************************************************/
class Player <T extends Card> {
	
	// required fields
	private String name;
	private int points;
	private Hand<T> hand;	
	private Player<T> next;
	
	// TO DO: add your implementation and JavaDoc

	public Player(String name){
		//constructor
		this.name = name;
      this.points = 0;
      this.hand = new Hand();
      this.next = null;
	}
		
	public void setNext(Player<T> p){
		//set next player
      this.next = p;
	}
	
	public Player<T> getNext(){
		//return next player
		return this.next;
	}
	
	public boolean hasNext() {
		// whether there is a player after me
		if(this.next != null){
         return true;
      }
      // returns false if the current player's next is null
      return false;
	}
	
	public int getPoints(){
		// return points of this player
		// determined by cards in hand
		// resetting points to 0 to recount
      this.points = 0;
      
      for(int i = 0; i < hand.numCards(); i++){
      
         this.points += hand.getCard(i).getPoints();
      }
      return this.points;
	}
		
	public String getName(){
		// return name of the player
      return this.name;
	}
	

	public boolean receiveCard(T c){
		// receive a card and add it to hand
		// return?
      
      //before adding card to hand, hasCard(c) checks
      //if the recieved card is already in the hand. If so,
      //method returns false
      if(this.hasCard(c)){
         return false;
      }
      hand.addCard(c);
      //points are adjusted after new card has been added to hand
      this.points += c.getPoints();
      return true;
	}
	
	public boolean hasCard(T c){
		// return checking: whether we have the card in hand
      if(hand.indexOf(c) != -1){
         return true;
      }
      return false;
	}
	
	public boolean playCard(T c){
		// give away one card from hand
		// return false if card not present
		if(hand.indexOf(c) == -1){
         return false;
      }
      else{
         //removing card from hand after card is played.
         //points are then updated.
         hand.removeCard(hand.indexOf(c));
         this.points = this.getPoints();
         return true;
      }
	}

	public T playCard(int index){
		// give away the card at index
		// throw RuntimeException for invalid index
      if(index > hand.numCards() - 1 || index < 0){
         throw new RuntimeException();
      }
      else{
         //card to be removed has its points subtracted from
         //the total points and the removed card is returned.
         this.points -= hand.getCard(index).getPoints();
         return hand.removeCard(index);
      }
	}
	
	

	//---------------------------------------------------
	//example test code... edit this as much as you want!
	// you will need working CardSwitch and Hand classes to run the given code
	
	
	public String toString(){
		// Not required; edit for your own testing 
		return "Player "+ name;
	}


	public static void main(String[] args) {
		CardSwitch card1 = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
		CardSwitch card2 = new CardSwitch(Card.Rank.JACK, Card.Suit.SPADES);
		CardSwitch card3 = new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS);
		Player<CardSwitch> player1 = new Player<CardSwitch>("Tom");
		Player<CardSwitch> player2 = new Player<CardSwitch>("Jerry");

		player1.receiveCard(card2);
		player1.receiveCard(card3);
		player2.receiveCard(card1);
		player1.setNext(player2);

		if (player1.getName().equals("Tom") && player1.getNext() == player2){
			System.out.println("Yay 1");
		}
		if (player1.hasCard(card2) == true && player1.getPoints() == 19){
			System.out.println("Yay 2");
		}
		if ((player2.hasNext()==false) && player1.playCard(0) == card2){
			System.out.println("Yay 3");
		}
	
	}


}