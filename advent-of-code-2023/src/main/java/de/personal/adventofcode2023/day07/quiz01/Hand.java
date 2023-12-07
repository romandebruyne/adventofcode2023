package de.personal.adventofcode2023.day07.quiz01;

import java.util.List;

public class Hand {
	private String values;
	private int bid;
	private int type;
	private List<Integer> strengths;
	
	public Hand(String values, int bid) {
		this.values = values;
		this.bid = bid;
		this.strengths = Hands.determineStrengthOfValues(this);
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
}