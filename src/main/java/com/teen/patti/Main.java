package com.teen.patti;

public class Main {

	public static void main(String[] args) {
	
		PokerTable pt = new PokerTable();
		
		pt.addPlayers("Akshay");
		pt.addPlayers("Mayank");
		pt.addPlayers("Jay");
		pt.addPlayers("Amit");
		
		pt.setPlayerCard();
		pt.winner();
	}
}
