/**************************************************************************
 * @author <Paul Lanham>
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: CardSwitch.java
 *
 * Description: <This class is used to represent cards with a rank and suit.>
 * <The class contains a method to check if two cards are equivalent and a method to calculate the point value of a card.>
 ***************************************************************************/

public class CardSwitch extends Card{

	// TO DO: fill the code below and add JavaDoc
	
	
	public CardSwitch(Rank r, Suit s){
		// constructor to create card for the game Switch
      super(r, s);
	}
	
	@Override
	public boolean equals(Card anotherCard){
		// checks if two cards equals and returns a boolean
		if(this.getRank().equals(anotherCard.getRank())){
         if(this.getSuit().equals(anotherCard.getSuit())){
            return true;
         }
      }
      // if one of the if statements is false
      // the method returns false
      return false;
	}
	
	@Override
    public int getPoints(){
	   // return points of the card
      if(this.getRank() == Card.Rank.ACE){ return 1;}
      if(this.getRank() == Card.Rank.TWO){ return 2;}
      if(this.getRank() == Card.Rank.THREE){ return 3;}
      if(this.getRank() == Card.Rank.FOUR){ return 4;}
      if(this.getRank() == Card.Rank.FIVE){ return 5;}
      if(this.getRank() == Card.Rank.SIX){ return 6;}
      if(this.getRank() == Card.Rank.SEVEN){ return 7;}
      if(this.getRank() == Card.Rank.EIGHT){ return 8;}
      if(this.getRank() == Card.Rank.NINE){ return 9;}
      else{ return 10;}    
    }
	
	@Override
	public String toString(){
		// convert card to string consisting of as "(rank,suit)"
		// see examples below for format
      String str = "(" + this.getRank() + "," + this.getSuit() + ")";
      return str;
	}
	
	//----------------------------------------------------
	//example test code... edit this as much as you want!
	public static void main(String[] args) {
		CardSwitch card = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
      
		if (card.getRank().equals(Card.Rank.ACE)){
			System.out.println("Yay 1");
		}
		
		if (card.toString().equals("(ACE,SPADES)")){
			System.out.println("Yay 2");
		}

		if (card.getPoints()==1){
			System.out.println("Yay 3");
		}
	}

}