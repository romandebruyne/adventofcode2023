package de.personal.adventofcode2023.day02.quiz02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExtendedCubeGameSolverTest {
	private ExtendedCubeGameSolver ecgs;
	private String testStringOne;
	private String testStringTwo;
	private String testStringThree;
	private String testStringFour;
	private String testStringFive;
	
	@BeforeEach
	public void setup() {
		System.out.println("ExtendedCubeGameSolver Test starts");
		this.ecgs = new ExtendedCubeGameSolver();
		this.testStringOne = "3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
		this.testStringTwo = "1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue";
		this.testStringThree = "8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";
		this.testStringFour = "1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red";
		this.testStringFive = "6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
	}
	
	@Test
	@DisplayName("Calculate power")
	public void testCalculatePower() {
		Assertions.assertEquals(125, this.ecgs.calculatePower(new ArrayList<>(Arrays.asList(5, 5, 5))));
	}
	
	@Test
	@DisplayName("Calculate sum of powers")
	public void testCalculateSumOfPowers() {
		Map<Integer, Map<Integer, Map<String, Integer>>> games = new HashMap<>();
		Map<Integer, List<Integer>> gamesWithMinimumCounts = new HashMap<>();

		games.put(1, this.ecgs.getSetData(this.testStringOne));
		games.put(2, this.ecgs.getSetData(this.testStringTwo));
		games.put(3, this.ecgs.getSetData(this.testStringThree));
		games.put(4, this.ecgs.getSetData(this.testStringFour));
		games.put(5, this.ecgs.getSetData(this.testStringFive));
		
		for (Entry<Integer, Map<Integer, Map<String, Integer>>> game : games.entrySet()) {
			gamesWithMinimumCounts.put(game.getKey(), this.ecgs.determineMinimumCubeCountsForOneGame(game.getValue()));
		}
		
		Assertions.assertEquals(2286, this.ecgs.calculateSumOfPowers(gamesWithMinimumCounts));
		Assertions.assertEquals(84911, this.ecgs.calculateSumOfPowers(this.ecgs.getAllGamesWithMinimumCounts()));
	}
	
	@Test
	@DisplayName("Determin the minimum count for all games")
	public void testDetermineMinimumCubeCountsAllGames() {
		Map<Integer, Map<Integer, Map<String, Integer>>> games = new HashMap<>();
		Map<Integer, List<Integer>> gamesWithMinimumCounts = new HashMap<>();

		games.put(1, this.ecgs.getSetData(this.testStringOne));
		games.put(2, this.ecgs.getSetData(this.testStringTwo));
		games.put(3, this.ecgs.getSetData(this.testStringThree));
		games.put(4, this.ecgs.getSetData(this.testStringFour));
		games.put(5, this.ecgs.getSetData(this.testStringFive));
		
		gamesWithMinimumCounts = this.ecgs.determineMinimumCubeCountsForAllGames(games);
		
		Assertions.assertEquals(6, gamesWithMinimumCounts.get(1).get(0));
		Assertions.assertEquals(4, gamesWithMinimumCounts.get(1).get(1));
		Assertions.assertEquals(2, gamesWithMinimumCounts.get(1).get(2));
	}
	
	@Test
	@DisplayName("Determin the minimum count for one game")
	public void testDetermineMinimumCubeCountsForGame() {
		Map<Integer, Map<Integer, Map<String, Integer>>> games = new HashMap<>();
		Map<Integer, List<Integer>> gamesWithMinimumCounts = new HashMap<>();

		games.put(1, this.ecgs.getSetData(this.testStringOne));
		games.put(2, this.ecgs.getSetData(this.testStringTwo));
		games.put(3, this.ecgs.getSetData(this.testStringThree));
		games.put(4, this.ecgs.getSetData(this.testStringFour));
		games.put(5, this.ecgs.getSetData(this.testStringFive));
		
		for (Entry<Integer, Map<Integer, Map<String, Integer>>> game : games.entrySet()) {
			gamesWithMinimumCounts.put(game.getKey(), this.ecgs.determineMinimumCubeCountsForOneGame(game.getValue()));
		}
		
		Assertions.assertEquals(6, gamesWithMinimumCounts.get(1).get(0));
		Assertions.assertEquals(4, gamesWithMinimumCounts.get(1).get(1));
		Assertions.assertEquals(2, gamesWithMinimumCounts.get(1).get(2));
		
		Assertions.assertEquals(4, gamesWithMinimumCounts.get(2).get(0));
		Assertions.assertEquals(1, gamesWithMinimumCounts.get(2).get(1));
		Assertions.assertEquals(3, gamesWithMinimumCounts.get(2).get(2));
		
		Assertions.assertEquals(6, gamesWithMinimumCounts.get(3).get(0));
		Assertions.assertEquals(20, gamesWithMinimumCounts.get(3).get(1));
		Assertions.assertEquals(13, gamesWithMinimumCounts.get(3).get(2));
		
		Assertions.assertEquals(15, gamesWithMinimumCounts.get(4).get(0));
		Assertions.assertEquals(14, gamesWithMinimumCounts.get(4).get(1));
		Assertions.assertEquals(3, gamesWithMinimumCounts.get(4).get(2));
		
		Assertions.assertEquals(2, gamesWithMinimumCounts.get(5).get(0));
		Assertions.assertEquals(6, gamesWithMinimumCounts.get(5).get(1));
		Assertions.assertEquals(3, gamesWithMinimumCounts.get(5).get(2));
	}
}