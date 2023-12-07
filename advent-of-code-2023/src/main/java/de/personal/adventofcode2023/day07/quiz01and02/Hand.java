package de.personal.adventofcode2023.day07.quiz01and02;

import java.util.List;

public class Hand {
	private String values;
	private int bid;
	private int type;
	private List<Integer> strengths;
	
	public Hand(String values, int bid) {
		this.values = values;
		this.bid = bid;
	}
	
	public String getValues() {
		return this.values;
	}
	
	public int getBid() {
		return this.bid;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public List<Integer> getStrengths() {
		return this.strengths;
	}
	
	public void setStrengths(List<Integer> strengths) {
		this.strengths = strengths;
	}
}