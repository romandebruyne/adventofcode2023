package de.personal.adventofcode2023.day02.quiz02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.personal.adventofcode2023.day02.quiz01.CubeGameSolver;

public class ExtendedCubeGameSolver extends CubeGameSolver {
	private Map<Integer, List<Integer>> allGamesWithMinimumCounts = new HashMap<>();
	
	public ExtendedCubeGameSolver() {
		super();
		this.allGamesWithMinimumCounts = determineMinimumCubeCountsForAllGames(this.getAllGames());
	}
	
	public int calculateSumOfPowers(Map<Integer, List<Integer>> gamesWithMinimumCounts) {
		int sumOfPowers = 0;
		
		for (List<Integer> minimumCounts : gamesWithMinimumCounts.values()) {
			sumOfPowers += calculatePower(minimumCounts);
		}
		
		return sumOfPowers;
	}
	
	public int calculatePower(List<Integer> counts) {
		return counts.stream().reduce(1, (a, b) -> a * b);
	}
	
	public Map<Integer, List<Integer>> determineMinimumCubeCountsForAllGames(
			Map<Integer, Map<Integer, Map<String, Integer>>> allGames) {
		Map<Integer, List<Integer>> resultMap = new HashMap<>();
		for (Entry<Integer, Map<Integer, Map<String, Integer>>> game : allGames.entrySet()) {
			resultMap.put(game.getKey(), determineMinimumCubeCountsForOneGame(game.getValue()));
		}
		return resultMap;
	}
	
	public List<Integer> determineMinimumCubeCountsForOneGame(Map<Integer, Map<String, Integer>> gameSets) {
		int minimumCountBlue = 0, minimumCountRed = 0, minimumCountGreen = 0;
		List<Integer> minimumCounts = new ArrayList<>();
		
		for (Entry<Integer, Map<String, Integer>> gameSet : gameSets.entrySet()) {
			if (gameSet.getValue().containsKey("blue")) {
				if (gameSet.getValue().get("blue") > minimumCountBlue) {
					minimumCountBlue = gameSet.getValue().get("blue");
				}				
			}
			
			if (gameSet.getValue().containsKey("red")) {
				if (gameSet.getValue().get("red") > minimumCountRed) {
					minimumCountRed = gameSet.getValue().get("red");
				}
			}
			
			if (gameSet.getValue().containsKey("green")) {
				if (gameSet.getValue().get("green") > minimumCountGreen) {
					minimumCountGreen = gameSet.getValue().get("green");
				}
			}
		}
		
		minimumCounts.addAll(Arrays.asList(minimumCountBlue, minimumCountRed, minimumCountGreen));
		
		return minimumCounts;
	}
	
	public Map<Integer, List<Integer>> getAllGamesWithMinimumCounts() {
		return this.allGamesWithMinimumCounts;
	}
}