package com.game.teenpatti.model;

public enum Suits {
  HEART("♥"),
  SPREAD("♠"),
  DIAMOND("♦"),
  CLUB("♣");

  private final String value;

  Suits(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
