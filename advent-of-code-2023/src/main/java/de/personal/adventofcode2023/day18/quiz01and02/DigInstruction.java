package de.personal.adventofcode2023.day18.quiz01and02;

public class DigInstruction {
	
	private int numberOfMoves;
	private char direction;
	private String colorCode;
	
	public DigInstruction(int numberOfMoves, char direction, String colorCode) {
		this.numberOfMoves = numberOfMoves;
		this.direction = direction;
		this.colorCode = colorCode;
	}
	
	public DigInstruction(int numberOfMoves, char direction) {
		this.numberOfMoves = numberOfMoves;
		this.direction = direction;
	}
	
	public int getNumberOfMoves() {
		return this.numberOfMoves;
	}
	public char getDirection() {
		return this.direction;
	}
	public String getColorCode() {
		return this.colorCode;
	}

}
