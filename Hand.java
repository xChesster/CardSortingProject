/**************************************************************************
 * @author <Paul Lanham>
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Hand.java
 *
 * Description: <Class representing a hand of cards by using an array that can be expanded to store the cards>
 * 
 ***************************************************************************/

public class Hand<T extends Card>{

	// TO DO: add your implementation and JavaDoc

	private T [] cards;
	private int numCards;
	
	public Hand(){
		// constructor
		// initial size of cards must be no greater than 5
      this.cards = (T[]) new Card[5];
      
	}
	
	public int numCards(){
		// return the number of cards in hand
		// O(1)
      return this.numCards;
	}	
	
	
	public T getCard(int index){
		// return card at index 
		// throw RuntimeException for invalid index
		// O(1)
      if(index > this.numCards() - 1){
         throw new RuntimeException();
      }
      else{
         return this.cards[index];
      }
	}

	public void setCard(int index, T c){
		// change the card at index to be c	
		// throw RuntimeException for invalid index
		// O(1)
      if(index > this.numCards() - 1){
         throw new RuntimeException();
      }
      else{
         this.cards[index] = c;
      }
	}

   //adds card to the end of hand. If hand is full,
   //array is expanded.
	public void addCard(T c){
		// add card c at the end 
		// O(N)
      
      //Before adding the card to the array,
      //checking to see if the array is at max capacity.
      //If the array is full, a temp array is created with double the size
      //of the previous array and the cards from the previous array are
      //copied to the temp array.
      if(this.numCards() == this.cards.length){
         T[] temp = (T[]) new Card[this.numCards() * 2];
         
         for(int i = 0; i < this.numCards(); i++){
            
            temp[i] = this.getCard(i);
            
         }
         //cards is set equal to the larger array
         this.cards = temp;
      }
      //the new card is appended to the array of cards and
      //the number of cards is incremented by 1
      this.cards[this.numCards()] = c;
      this.numCards++;
	}
	
	
	public int indexOf(T c){
		 // find the index of a given card c, 
		 // returns -1 if not found	
		 // O(N) 
       
       //Loops through the array of cards until card c is found.
       //If the card is not found, the method returns -1.
      for(int i = 0; i < this.numCards(); i++){
      
         if(this.getCard(i) == c){
            return i;
         }
      }
      
      return -1;
	}
		
	 
	public T removeCard(int index){
		// remove the card at index, 
		// throw RuntimeException for invalid index
		// O(N)
      if(index > this.numCards() - 1){
         throw new RuntimeException();
      }
      
      //temp variable (removed) to keep track of the removed card
      //after its removal from the array
      T removed;
      removed = this.cards[index];
      
      // if card to be removed is at end of array,
      // card is removed and returned
      if(index == this.numCards() - 1){
         this.cards[index] = null;
         this.numCards--;
         return removed;
      }
      
      // shifting cards over after card is removed
      for(int i = index; i < this.numCards(); i++){
         
         this.cards[i] = this.cards[i + 1];
      }
      //card is removed by setting its index to null
      this.cards[this.numCards() - 1] = null;
      //number of cards is decremented by 1 since card was removed
      this.numCards--;
      return removed;
	}
	
	public boolean removeCard(T c){
		// remove card c, 
		// returns false if no such card
		// O(N)
      
      //loops through array and returns true if it finds the card
      //to be removed (c). Returns false otherwise.
      for(int i = 0; i < this.numCards(); i++){
      
         if(this.cards[i] == c){
            return true;
         }
      }
      return false;
	}
	
	
  
	// --------------------------------------------------------
	// example test code... edit this as much as you want!
	// you will need a working CardSwitch class to run the given code


	// Not required, update for your testing purpose
	@Override
	public String toString(){
		// return string representation of hand
		// update if you want to include information for all cards in hand
		return "Hand with "+numCards+" cards";
		
		
  	}


	public static void main(String[] args) {
	
		CardSwitch card1 = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
		CardSwitch card2 = new CardSwitch(Card.Rank.JACK, Card.Suit.SPADES);
		CardSwitch card3 = new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS);
		
		
		Hand<CardSwitch> myHand = new Hand<CardSwitch>();
		myHand.addCard(card1);
		myHand.addCard(card2);
		
		if ((myHand.numCards() == 2) && (myHand.getCard(0).equals(card1))){
			System.out.println("Yay 1");
		}
		
		myHand.addCard(card3);
		
		if ( card2.equals(myHand.removeCard(1)) && myHand.getCard(1).equals(card3)){
			System.out.println("Yay 2");
		}

		if ((myHand.indexOf(card1)==0) && (myHand.indexOf(card2) == -1 )){
			System.out.println("Yay 3");
		}

	}


}