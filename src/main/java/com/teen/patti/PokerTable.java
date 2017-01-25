package com.teen.patti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerTable {

	Deck deck = null;
	Players pl = null;

	List<String> randCardsList = new ArrayList<>();
	List<String> playerList = new ArrayList<>();
	List<String> cardList = new ArrayList<>();
	Map<String, List<String>> finalList = new HashMap<>();
	List<Game> result = new ArrayList<Game>();
	
	public PokerTable() {
		super();
		deck = new Deck();
		pl = new Players();
	}

	public void addPlayers(String name) {
		pl.addPlayers(name);
	}
	
	public void shuffle() {
		Collections.shuffle(deck.getCollectionCards());
		randCardsList.addAll(deck.getCollectionCards());
	}
	
	public List<String> getRandCardsList() {
		return randCardsList;
	}
		
	public List<Game> getResult() {
		return result;
	}

	public void setPlayerCard() {
		shuffle();
		Integer nop = pl.getPlayers().size();
		for(int i=0;i<nop;i++) {
			int t = i;
			playerList.add(pl.getPlayers().get(i));
			System.out.println(pl.getPlayers().get(i));
			for(int j=0;j<3;j++) {
				cardList.add(getRandCardsList().get(t));
				System.out.println(getRandCardsList().get(t));
				t += nop;
			}
		}
		//System.out.println(playerList);
		//System.out.println(cardList);
		
		int c = 0;
		for(int i=0;i<pl.getPlayers().size();i++) {
			finalList.put(pl.getPlayers().get(i), Arrays.asList(cardList.get(c++), cardList.get(c++), cardList.get(c++)));
		}
		System.out.println(finalList);
	}
	
	public int cardValue(String cardVal) {
		int c = 0;
		for(Cards val:Cards.values()){
			String card = val.getValue();
			if(card.equals(cardVal)) {
				c=val.getVal();
			}
		}
		return c;
	}
	
	public void cardIdentify() {
		int temp=0,k=0;
		int seq[] = new int[3];
		String orinalCard;
		
		for(int j=0;j<finalList.size();j++) {
		
			orinalCard = finalList.get(pl.getPlayers().get(j)).toString();
			String c1 = finalList.get(pl.getPlayers().get(j)).get(temp).toString();
			String c2 = finalList.get(pl.getPlayers().get(j)).get(temp+1).toString();
			String c3 = finalList.get(pl.getPlayers().get(j)).get(temp+2).toString();
			
			String ca1 = finalList.get(pl.getPlayers().get(j)).get(temp).toString();
			String ca2 = finalList.get(pl.getPlayers().get(j)).get(temp+1).toString();
			String ca3 = finalList.get(pl.getPlayers().get(j)).get(temp+2).toString();
			
			String d1="";
			String d2="";
			String d3="";
			
			if(c1.length() == 3) {
				c1=c1.substring(2);
				d1=ca1.substring(0, 2);
			}else{
				c1=c1.substring(1);
				d1=ca1.substring(0, 1);
			}
			
			if(c2.length() == 3) {
				c2=c2.substring(2);
				d2=ca2.substring(0, 2);
			}else{
				c2=c2.substring(1);
				d2=ca2.substring(0, 1);
			}
			
			if(c3.length() == 3){
				c3=c3.substring(2);
				d3=ca3.substring(0, 2);
			}else{
				c3=c3.substring(1);
				d3=ca3.substring(0, 1);
			}
			
			seq[0] = cardValue(d1);
			seq[1] = cardValue(d2);
			seq[2] = cardValue(d3);
	
			Arrays.sort(seq);
			
			if(seq[2] == 14 && seq[0] == 2 && seq[1] == 3) {
				seq[0] = 1;
				seq[1] = 2;
				seq[2] = 3;
			}
			int total=0;
			int sum = 0;
			if(d1.equals(d2) && d2.equals(d3) && d3.equals(d1)) { //For Triple
				total = total + 5000000;
				sum=priority(seq,total,sum);
				System.out.println("Reason : Triple");
				
			}else if(seq[0]+1 == seq[1] && seq[0]+2 == seq[2]) { //For Sequence
				total = total + 3000000;
				if(seq[0] == 1 && seq[1] == 2 && seq[2] == 3) {
					seq[0] = 2;
					seq[1] = 3;
					seq[2] = 14;
				}
				if(c1.equals(c2) && c2.equals(c3) && c3.equals(c1)) {
					total = total + 2500000;
					sum=priority(seq,total,sum);
					System.out.println("Reason : Pure Sequence");
				}
				sum=priority(seq,total,sum);
				System.out.println("Reason : Sequence");
				
			}else if(c1.equals(c2) && c2.equals(c3) && c3.equals(c1)) { // For Color
				total = total + 1000000;
				sum=priority(seq,total,sum);
				System.out.println("Reason : Color");
				
			}else if(d1.equals(d2) || d2.equals(d3) || d3.equals(d1)) {
				total = total + 500000;
				k = seq[1];
				for(int i=1;i<=14;i++) {
					if(k == i) {
						sum = total + seq[0] + seq[1] + seq[2] + (k * k * k);
					}
				}
				System.out.println("Reason : Double");
				
			}else {
				sum=priority(seq,total,sum);
				System.out.println("Reason : Priority");
			}
			System.out.println("Player : " + pl.getPlayers().get(j));
			System.out.println("Priority : " + total);
			System.out.println("Sum : "+ sum);
			System.out.println("=========================================");
			result.add(new Game(pl.getPlayers().get(j),sum,orinalCard));
		}	
		
	}
	
	public int priority(int seq[],int total,int sum) {
		int minSeq = 2;
		int maxSeq = 14;
		int[] seqValues = {600, 800, 1000, 2000, 4000, 6000, 8000, 10000, 20000, 40000, 60000, 80000, 100000};
		total = total + seq[0] + seq[1] + seq[2];
		
		for(int i = minSeq; i <= maxSeq; i++) {
			if (seq[2] == i) {
				total += seqValues[i-minSeq];
				break;
			}
		}
		return total;
	}
	
	public void result() {
		result.sort(Comparator.comparing(g -> g.getPriority()));
		Collections.reverse(result);
		Game winner = result.get(0);
		System.out.println("Winner Player");
		System.out.println("===========================================");
		System.out.println("Name : " + winner.getWinnerName());
		System.out.println("Priority : " + winner.getPriority());
		System.out.println("Card : " + winner.getCards());
	}
	
	public void winner() {
		cardIdentify();
		result();
	}
}
