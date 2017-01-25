package com.teen.patti;

import java.util.ArrayList;
import java.util.List;

public class Players {
	
	List<String> players = new ArrayList<>();
	
	public Players() {
		super();
	}

	public void addPlayers(String name) {
		players.add(name);
	}
					
	public List<String> getPlayers() {
		return players;
	}
}
