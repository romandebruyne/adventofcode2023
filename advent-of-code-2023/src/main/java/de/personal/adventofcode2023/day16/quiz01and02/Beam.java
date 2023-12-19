package de.personal.adventofcode2023.day16.quiz01and02;

public class Beam {
	
	private int row;
	private int col;
	private char direction;
	
	public Beam(int row, int col, char direction) {
		this.row = row;
		this.col = col;
		this.direction = direction;
	}

	public int getRow() {
		return this.row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public char getDirection() {
		return this.direction;
	}
	
	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		
		if (object == null) {
			return false;
		}
		
		if (getClass() != object.getClass()) {
			return false;
		}
		
		Beam other = (Beam) object;
		return this.row == other.row && this.col == other.col && this.direction == other.direction; 
		
	}

	@Override
	public String toString() {
		return this.row + " " + this.col + ", dir: " + this.direction;
	}

}
