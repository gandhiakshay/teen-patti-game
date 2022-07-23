package com.game.teenpatti.service;

import com.game.teenpatti.model.Cards;
import com.game.teenpatti.model.Game;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerTableService {

  @Inject private DeckService deckService;
  @Inject private PlayerService playerService;

  private List<String> randCardsList = new ArrayList<>();
  private List<String> playerList = new ArrayList<>();
  private List<String> cardList = new ArrayList<>();
  private Map<String, List<String>> finalList = new HashMap<>();
  private List<Game> result = new ArrayList<Game>();

  public void addPlayers(String name) {
    playerService.addPlayers(name);
  }

  public void shuffle() {
    Collections.shuffle(deckService.getCollectionCards());
    randCardsList.addAll(deckService.getCollectionCards());
  }

  public void setPlayerCard() {
    shuffle();
    Integer nop = playerService.getPlayers().size();
    if (nop > 17) {
      System.err.println(
          "WARNING : Total players are " + nop + ". So, Max 17 players can play from one deck.");
      nop = 17;
    }
    System.out.println("Playing Players");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    for (int i = 0; i < nop; i++) {
      int t = i;
      playerList.add(playerService.getPlayers().get(i));
      System.out.print("☺ " + playerService.getPlayers().get(i) + " : ");
      for (int j = 0; j < 3; j++) {
        cardList.add(randCardsList.get(t));
        System.out.print(randCardsList.get(t) + " ");
        t += nop;
      }
      System.out.println("");
    }
    // System.out.println(playerList);
    // System.out.println(cardList);

    int c = 0;
    for (int i = 0; i < playerService.getPlayers().size(); i++) {
      if (i == 17) {
        break;
      }
      finalList.put(
          playerService.getPlayers().get(i),
          Arrays.asList(cardList.get(c++), cardList.get(c++), cardList.get(c++)));
    }
    System.out.println("------------------------------------------\n");
  }

  public void winner() {
    cardIdentify();
    result();
  }

  private int cardValue(String cardVal) {
    int c = 0;
    for (Cards val : Cards.values()) {
      String card = val.getValue();
      if (card.equals(cardVal)) {
        c = val.getVal();
      }
    }
    return c;
  }

  private void cardIdentify() {
    int k = 0;
    int seq[] = new int[3];
    String orinalCard;

    for (int j = 0; j < finalList.size(); j++) {
      String pl = playerService.getPlayers().get(j);
      List<String> playerCards = finalList.get(playerService.getPlayers().get(j));

      orinalCard = finalList.get(pl).toString();
      String first = playerCards.get(0);
      String second = playerCards.get(1);
      String third = playerCards.get(2);

      String c1 = first.substring(0, 1);
      String c2 = second.substring(0, 1);
      String c3 = third.substring(0, 1);

      String d1 = first.substring(1);
      String d2 = second.substring(1);
      String d3 = third.substring(1);

      seq[0] = cardValue(d1);
      seq[1] = cardValue(d2);
      seq[2] = cardValue(d3);

      Arrays.sort(seq);

      if (seq[2] == 14 && seq[0] == 2 && seq[1] == 3) {
        seq[0] = 1;
        seq[1] = 2;
        seq[2] = 3;
      }
      int total = 0;
      int sum = 0;
      if (d1.equals(d2) && d2.equals(d3)) { // For Triple
        total = total + 5000000;
        sum = priority(seq, total, sum);
        System.out.println("Reason : Triple");

      } else if (seq[0] + 1 == seq[1] && seq[0] + 2 == seq[2]) { // For Sequence
        total = total + 3000000;
        if (seq[0] == 1 && seq[1] == 2 && seq[2] == 3) {
          seq[0] = 2;
          seq[1] = 3;
          seq[2] = 14;
        }
        if (c1.equals(c2) && c2.equals(c3) && c3.equals(c1)) {
          total = total + 2500000;
          sum = priority(seq, total, sum);
          System.out.println("Reason : Pure Sequence");
        }
        sum = priority(seq, total, sum);
        System.out.println("Reason : Sequence");
      } else if (c1.equals(c2) && c2.equals(c3) && c3.equals(c1)) { // For Color
        total = total + 1000000;
        sum = priority(seq, total, sum);
        System.out.println("Reason : Color");
      } else if (d1.equals(d2) || d2.equals(d3) || d3.equals(d1)) {
        total = total + 500000;
        k = seq[1];
        for (int i = 1; i <= 14; i++) {
          if (k == i) {
            sum = total + seq[0] + seq[1] + seq[2] + (k * k * k);
            break;
          }
        }
        System.out.println("Reason : Double");
      } else {
        sum = priority(seq, total, sum);
        System.out.println("Reason : Higher Order");
      }
      System.out.println("Player : " + playerService.getPlayers().get(j));
      System.out.println("Priority : " + total);
      System.out.println("Sum : " + sum);
      System.out.println("=========================================");
      result.add(new Game(playerService.getPlayers().get(j), sum, orinalCard));
    }
  }

  private int priority(int seq[], int total, int sum) {
    int minSeq = 2;
    int maxSeq = 14;
    int[] seqValues = {
      600, 800, 1000, 2000, 4000, 6000, 8000, 10000, 20000, 40000, 60000, 80000, 100000
    };
    total = total + seq[0] + seq[1] + seq[2];

    for (int i = minSeq; i <= maxSeq; i++) {
      if (seq[2] == i) {
        total += seqValues[i - minSeq];
        break;
      }
    }
    return total;
  }

  private void result() {
    result.sort(Comparator.comparing(g -> g.getPriority()));
    Collections.reverse(result);
    Game winner = result.get(0);
    System.out.println("\n*********************************************");
    System.out.println("♛ Winner Player ♛");
    System.out.println("*********************************************");
    System.out.println("Name : " + winner.getWinnerName());
    System.out.println("Priority : " + winner.getPriority());
    System.out.println("Card : " + winner.getCards());
  }
}
