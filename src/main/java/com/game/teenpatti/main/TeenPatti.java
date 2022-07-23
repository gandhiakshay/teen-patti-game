package com.game.teenpatti.main;

import com.game.teenpatti.module.TeenPattiModule;
import com.game.teenpatti.service.PokerTableService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class TeenPatti {

  public static void main(String[] args) {
    Injector inject = Guice.createInjector(new TeenPattiModule());
    PokerTableService pokerTableService = inject.getInstance(PokerTableService.class);

    pokerTableService.addPlayers("Player1");
    pokerTableService.addPlayers("Player2");
    pokerTableService.addPlayers("Player3");
    pokerTableService.addPlayers("Player4");

    pokerTableService.setPlayerCard();
    pokerTableService.winner();
  }
}
