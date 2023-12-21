package de.personal.adventofcode2023.day21.quiz01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Gardener {
	
	private int row;
	private int col;
	private int numOfSteps;
	private Set<int[]> visitedGardenPlots;
	
	public Gardener() {
		this.visitedGardenPlots = new HashSet<>();
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
	
	public int getNumOfSteps() {
		return this.numOfSteps;
	}
	
	public void addNumOfSteps() {
		this.numOfSteps++;
	}
	
	public Set<int[]> getVisitedGardenPlots() {
		return this.visitedGardenPlots;
	}

}
