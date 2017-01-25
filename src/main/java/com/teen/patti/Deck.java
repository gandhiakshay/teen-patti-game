package com.teen.patti;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	
	List<String> collectionCards = new ArrayList<>();
	
	public Deck() {
		Suits suits[] = Suits.values();
		Cards cards[] = Cards.values();
		
		for(int i=0; i < suits.length; i++) {
			for(int j=0; j < cards.length; j++) {
				collectionCards.add(suits[i].getValue()+cards[j].getValue());
			}
		}
	}

	public List<String> getCollectionCards() {
		return collectionCards;
	}

	public void setCollectionCards(List<String> collectionCards) {
		this.collectionCards = collectionCards;
	}
}
