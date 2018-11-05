/**************************************************************************
 * @author Yutao Zhong and Jitin Krishnan
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Deck.java
 *
 * Description: Class representing a deck of cards with basic functionalities
 * of shuffling, adding, dealing, etc.
 *
 * TASK: Comment using JavaDoc and show the Big-O runtime of each method.
 * Code on this file should NOT be modified.
 * 
 ***************************************************************************/

public class Deck<T extends Card> {
	
	private Hand<T> setOfCards;
	
	public Deck(){
		setOfCards = new Hand<T>();
	}
	
   // O(1)
   // This method checks to see if the current hand contains the card to be added.
   // If the hand already contains the card, the method returns false.
   // Otherwise, the card is added to the hand (setOfCards) and the method returns true.
	public boolean addCard(T c){
		if (hasCard(c))
			return false;
		setOfCards.addCard(c);
		return true;
	}
	
   // O(N)
   // This method checks if card c is in the hand (setOfCards) and returns false if it is.
   // Otherwise, returns true.
	public boolean hasCard(T c){
		return (setOfCards.indexOf(c)!=-1);
	
	}

   // O(N)
   // This method loops from the last index of the hand (setOfCards) to the first
   // index and uses a rand variable that gives a random number between 0 and the number of cards in the hand.
   // the rand variable is used to change the position of the cards by generating a random new index.
	public void shuffle() {
		for ( int i = setOfCards.numCards()-1; i >= 0; i-- ) {
			int rand = (int)(Math.random()*(i+1));
         // temp variable to keep track of card so that another card can takes its spot in
         // the hand and the positions of the two cards can be swapped.
	        T temp = setOfCards.getCard(i);
            	setOfCards.setCard(i, setOfCards.getCard(rand));
            	setOfCards.setCard(rand, temp);
	    }
	}
	
   // O(N)
   // This method deals a card by removing the card at the last index of the hand and returning it.
	public T dealNextCard() {
		
      // If hand is empty, method returns null
		if(setOfCards.numCards()==0) return null;
		T temp = this.setOfCards.removeCard(setOfCards.numCards()-1);
		return temp;
	}

   // O(1)
   // This method calls method from hand that checks to see if the current hand (setOfCards)
   // has 0 cards or not.
	public boolean isEmpty() {
		return this.setOfCards.numCards() == 0;
	}

   // O(1)
   // This method calls numCards() method from hand and returns the number of cards
   // in the current hand (setOfCards)
	public int cardCount(){
		return this.setOfCards.numCards();
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("Deck ");
    		int numCards = cardCount();
    		if (numCards ==0){
    			sb.append("currently with no cards.");
    		}
    		else{
    			sb.append("with "+numCards+ " cards:\n");
    			sb.append(setOfCards.toString());
    		}
    		return sb.toString();
  	}

}
