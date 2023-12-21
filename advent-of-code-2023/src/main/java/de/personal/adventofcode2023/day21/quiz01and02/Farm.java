package de.personal.adventofcode2023.day21.quiz01and02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Farm {
	private static final int[] NORTH = {-1, 0}; 
	private static final int[] SOUTH = {1, 0}; 
	private static final int[] WEST = {0, -1}; 
	private static final int[] EAST = {0, 1}; 
	private char[][] structure;
	private List<int[]> currentGardenerPositions;
	
	public Farm(String inputPath, int size) {
		this.structure = Day21Utils.importFarmStructure(inputPath, size);
		this.currentGardenerPositions = new ArrayList<>(Arrays.asList(findStartingPosition()));
	}
	
	public int[] findStartingPosition() {
		int[] startingPosition = new int[2];
		
		for (int row = 0; row < this.structure.length; row++) {
			for (int col = 0; col < this.structure[0].length; col++) {
				if (this.structure[row][col] == 'S') {
					startingPosition[0] = row;
					startingPosition[1] = col;
				}
			}
		}
		
		return startingPosition;
	}
	
	public long getNumberOfGardenPlotsAfterXSteps(int maxSteps, boolean partOne) {
		for (int i = 0; i < maxSteps; i++) {
			moveGardenerByOneStep(partOne);
			System.out.println(i);
		}
		
		return this.currentGardenerPositions.size();
	}
	
	public void moveGardenerByOneStep(boolean partOne) {
		List<int[]> copyOfCurrentGardenerPositions = new ArrayList<>(this.currentGardenerPositions);
		int currentNumOfPositions = copyOfCurrentGardenerPositions.size();
		
		for (int i = 0; i < currentNumOfPositions; i++) {
			if (partOne) {
				getNextFeasiblePositionsPartOne(copyOfCurrentGardenerPositions.get(i));
			} else {
				getNextFeasiblePositionsPartTwo(copyOfCurrentGardenerPositions.get(i));
			}
		}
	}
	
	public void getNextFeasiblePositionsPartOne(int[] currPosition) {
		List<int[]> directions = Arrays.asList(NORTH, SOUTH, WEST, EAST);
		int[] gardenersNewPosition;
		
		for (int[] dir : directions) {
			gardenersNewPosition = new int[2];
			gardenersNewPosition[0] = currPosition[0] + dir[0];
			gardenersNewPosition[1] = currPosition[1] + dir[1];
			
			if (isInBound(gardenersNewPosition) && isGardenPlot(gardenersNewPosition)
					&& !Day21Utils.positionIsInList(gardenersNewPosition, this.currentGardenerPositions)) {
				this.currentGardenerPositions.add(gardenersNewPosition);
			}
		}
		
		this.currentGardenerPositions.remove(currPosition);
	}
	
	public void getNextFeasiblePositionsPartTwo(int[] currPosition) {
		List<int[]> directions = Arrays.asList(NORTH, SOUTH, WEST, EAST);
		int[] gardenersNewPosition;
		
		for (int[] dir : directions) {
			gardenersNewPosition = new int[2];
			
			gardenersNewPosition[0] = currPosition[0] >= 0 ? currPosition[0] + dir[0] : currPosition[0] - dir[0];
			gardenersNewPosition[1] = currPosition[1] + dir[1];
			
			if (isGardenPlot(gardenersNewPosition)
					&& !Day21Utils.positionIsInList(gardenersNewPosition, this.currentGardenerPositions)) {
				this.currentGardenerPositions.add(gardenersNewPosition);
			}
		}
		
		this.currentGardenerPositions.remove(currPosition);
	}

	public int[] correctPosition(int[] position) {
		int[] correctedPosition = {position[0], position[1]};
		
		if (position[0] < 0 || position[1] < 0) {
			while (correctedPosition[0] < 0) {
				correctedPosition[0] = correctedPosition[0] + this.structure.length;
			}
			
			while (correctedPosition[1] < 0) {
				correctedPosition[1] = correctedPosition[1] + this.structure[0].length;
			}
			
		}
		
		if (position[0] >= this.structure.length || position[1] >= this.structure[0].length) {
			while (correctedPosition[0] >= this.structure.length) {
				correctedPosition[0] = correctedPosition[0] - this.structure.length;
			}
			
			while (correctedPosition[1] >= this.structure[0].length) {
				correctedPosition[1] = correctedPosition[1] - this.structure[0].length;
			}
		}
		
		return correctedPosition;
	}
	
	private boolean isGardenPlot(int[] position) {
		if (!isInBound(position)) {
			int[] correctedPosition = correctPosition(position);
			return this.structure[correctedPosition[0]][correctedPosition[1]] != '#';
			
		} else {
			return this.structure[position[0]][position[1]] != '#';
		}
	}
	
	private boolean isInBound(int[] position) {
		int rowMax = this.structure.length, colMax = this.structure[0].length;
		return position[0] >=0 && position[0] < rowMax && position[1] >= 0 && position[1] < colMax;
	}
}
