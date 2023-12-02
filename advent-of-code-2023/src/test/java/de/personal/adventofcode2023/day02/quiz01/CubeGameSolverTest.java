package de.personal.adventofcode2023.day02.quiz01;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CubeGameSolverTest {
	private CubeGameSolver cgs;
	private String testStringOne;
	private String testStringTwo;
	private String testStringThree;
	private String testStringFour;
	private String testStringFive;
	
	@BeforeEach
	public void setup() {
		System.out.println("CubeGameSolver Test starts");
		this.cgs = new CubeGameSolver();
		this.testStringOne = "3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
		this.testStringTwo = "1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue";
		this.testStringThree = "8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";
		this.testStringFour = "1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red";
		this.testStringFive = "6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
	}
	
	@Test
	@DisplayName("Get game ID")
	public void testGetGameId() {
		Assertions.assertEquals(1, this.cgs.getGameId("Game 1"));
		Assertions.assertEquals(123234, this.cgs.getGameId("Game 123234"));
	}
	
	@Test
	@DisplayName("Get set data")
	public void testGetSetData() {
		Map<Integer, Map<String, Integer>> resultMap = new HashMap<>();
		resultMap = this.cgs.getSetData(this.testStringOne);
		
		Assertions.assertEquals(3, resultMap.size());
		Assertions.assertEquals(3, resultMap.get(1).get("blue"));
		Assertions.assertEquals(4, resultMap.get(1).get("red"));
		Assertions.assertEquals(1, resultMap.get(2).get("red"));
		Assertions.assertEquals(2, resultMap.get(2).get("green"));
		Assertions.assertEquals(6, resultMap.get(2).get("blue"));
		Assertions.assertEquals(2, resultMap.get(3).get("green"));
	}
	
	@Test
	@DisplayName("Is the game possible?")
	public void testIsGamePossible() {
		Map<Integer, Map<Integer, Map<String, Integer>>> games = new HashMap<>();
		
		games.put(1, this.cgs.getSetData(this.testStringOne));
		games.put(2, this.cgs.getSetData(this.testStringTwo));
		games.put(3, this.cgs.getSetData(this.testStringThree));
		games.put(4, this.cgs.getSetData(this.testStringFour));
		games.put(5, this.cgs.getSetData(this.testStringFive));
		
		Assertions.assertEquals(true, this.cgs.isGamePossible(games.get(1)));
		Assertions.assertEquals(true, this.cgs.isGamePossible(games.get(2)));
		Assertions.assertEquals(false, this.cgs.isGamePossible(games.get(3)));
		Assertions.assertEquals(false, this.cgs.isGamePossible(games.get(4)));
		Assertions.assertEquals(true, this.cgs.isGamePossible(games.get(5)));
	}
	
	@Test
	@DisplayName("Calculate sum of possible game IDs")
	public void testCalculateSumOfPossibleGameIds() {
		Assertions.assertEquals(2006, this.cgs.calculateSumOfPossibleGameIds());
	}
}