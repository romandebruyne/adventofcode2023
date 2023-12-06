package de.personal.adventofcode2023.day06.quiz01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoatRaceSolver {
	private List<Integer> allTimes = new ArrayList<>(Arrays.asList(55, 99, 97, 93));
	private List<Integer> allDistances = new ArrayList<>(Arrays.asList(401, 1485, 2274, 1405));
	
	public int getPotentialWins(List<Integer> times, List<Integer> distances) {
		int potentialWin;
		List<Integer> potentialWins = new ArrayList<>();
		
		for (int i = 0; i < times.size(); i++) {
			potentialWin = 0;
			
			for (int millisecond = 1; millisecond <= times.get(i); millisecond++) {
				if (millisecond * (times.get(i) - millisecond) > distances.get(i)) {
					potentialWin++;
				}
			}
			
			potentialWins.add(potentialWin);
		}
		
		return potentialWins.stream().reduce(1, (a, b) -> a * b);
	}

	public List<Integer> getAllTimes() {
		return this.allTimes;
	}

	public List<Integer> getAllDistances() {
		return this.allDistances;
	}

}
