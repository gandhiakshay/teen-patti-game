package com.teen.patti;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	
	List<String> collectionCards = new ArrayList<>();
	
	public Deck() {
		super();
		String suits[] = new String[4];
		suits[0] = Suits.HEART.getValue();
		suits[1] = Suits.SPREAD.getValue();
		suits[2] = Suits.DIAMOND.getValue();
		suits[3] = Suits.CLUB.getValue();
		
		for(int i=0;i<4;i++) {
			collectionCards.add(Cards.ONE.getValue() + suits[i]);
			collectionCards.add(Cards.TWO.getValue() + suits[i]);
			collectionCards.add(Cards.THREE.getValue() + suits[i]);
			collectionCards.add(Cards.FOUR.getValue() + suits[i]);
			collectionCards.add(Cards.FIVE.getValue() + suits[i]);
			collectionCards.add(Cards.SIX.getValue() + suits[i]);
			collectionCards.add(Cards.SEVEN.getValue() + suits[i]);
			collectionCards.add(Cards.EIGHT.getValue() + suits[i]);
			collectionCards.add(Cards.NINE.getValue() + suits[i]);
			collectionCards.add(Cards.TEN.getValue() + suits[i]);
			collectionCards.add(Cards.ELEVEN.getValue() + suits[i]);
			collectionCards.add(Cards.TWELVE.getValue() + suits[i]);
			collectionCards.add(Cards.THARTEEN.getValue() + suits[i]);
		}
	}

	public List<String> getCollectionCards() {
		return collectionCards;
	}

	public void setCollectionCards(List<String> collectionCards) {
		this.collectionCards = collectionCards;
	}
}
