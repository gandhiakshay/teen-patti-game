package com.game.teenpatti.model;

public enum Cards {
  ONE("A", 14),
  TWO("2", 2),
  THREE("3", 3),
  FOUR("4", 4),
  FIVE("5", 5),
  SIX("6", 6),
  SEVEN("7", 7),
  EIGHT("8", 8),
  NINE("9", 9),
  TEN("10", 10),
  ELEVEN("J", 11),
  TWELVE("Q", 12),
  THARTEEN("K", 13);

  private final String value;
  private final int val;

  Cards(String value, int val) {
    this.value = value;
    this.val = val;
  }

  public String getValue() {
    return value;
  }

  public int getVal() {
    return val;
  }
};
