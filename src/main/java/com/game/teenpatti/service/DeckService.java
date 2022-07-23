package com.game.teenpatti.service;

import com.game.teenpatti.model.Cards;
import com.game.teenpatti.model.Suits;
import java.util.ArrayList;
import java.util.List;

public class DeckService {

  private List<String> collectionCards = new ArrayList<>();

  public DeckService() {
    Suits suits[] = Suits.values();
    Cards cards[] = Cards.values();

    for (int i = 0; i < suits.length; i++) {
      for (int j = 0; j < cards.length; j++) {
        collectionCards.add(suits[i].getValue() + cards[j].getValue());
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
