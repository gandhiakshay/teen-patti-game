package com.game.teenpatti.service;

import java.util.ArrayList;
import java.util.List;

public class PlayerService {

  private List<String> players = new ArrayList<>();

  public PlayerService() {
    super();
  }

  public void addPlayers(String name) {
    players.add(name);
  }

  public List<String> getPlayers() {
    return players;
  }
}
