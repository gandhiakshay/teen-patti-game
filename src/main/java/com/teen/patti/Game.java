package com.teen.patti;

public class Game {

  private String winnerName;
  private int priority;
  private String cards;

  public Game(String winnerName, int priority, String cards) {
    super();
    this.winnerName = winnerName;
    this.priority = priority;
    this.cards = cards;
  }

  public String getWinnerName() {
    return winnerName;
  }

  public int getPriority() {
    return priority;
  }

  public String getCards() {
    return cards;
  }
}
