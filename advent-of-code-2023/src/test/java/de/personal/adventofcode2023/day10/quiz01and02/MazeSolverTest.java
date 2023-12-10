package de.personal.adventofcode2023.day10.quiz01and02;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeSolverTest {
	
	private List<String> testDataOne;
	private List<String> testDataTwo;
	private List<String> testDataThree;
	
	@BeforeEach
	public void setup() {
		this.testDataOne = MazeSolver.importAllLines("./res/aoc_day10_testinput1.txt");
		this.testDataTwo = MazeSolver.importAllLines("./res/aoc_day10_testinput2.txt");
		this.testDataThree = MazeSolver.importAllLines("./res/aoc_day10_testinput3.txt");
	}
	
	@Test
	public void testDetermineShapeOfS() {
		List<Integer> startingPosition = MazeSolver.findStartingPosition(this.testDataOne);
		Assertions.assertEquals('F', MazeSolver.determineShapeOfS(this.testDataOne, startingPosition));
		
		List<String> realData = MazeSolver.importAllLines("./res/aoc_day10_input.txt");
		startingPosition = MazeSolver.findStartingPosition(realData);
		Assertions.assertEquals('7', MazeSolver.determineShapeOfS(realData, startingPosition));
	}
	
	@Test
	public void testDetermineNextPipe() {
		List<Integer> startingPosition = MazeSolver.findStartingPosition(this.testDataOne);
		List<Integer> nextPipePosition = startingPosition;
		List<Integer> previousPipePosition = new ArrayList<>();
		
		this.testDataOne.set(startingPosition.get(0),
				this.testDataOne.get(startingPosition.get(0)).replace('S', 
						MazeSolver.determineShapeOfS(this.testDataOne, startingPosition)));
		
		nextPipePosition = MazeSolver.determineNextPipe(this.testDataOne, nextPipePosition, previousPipePosition);
	}
	
	@Test
	public void testDetermineMaximumSteps() {
		Assertions.assertEquals(4, MazeSolver.determineMaximumSteps(MazeSolver.findTheLoop(this.testDataOne)));
		Assertions.assertEquals(6979, MazeSolver.determineMaximumSteps(
				MazeSolver.findTheLoop(MazeSolver.importAllLines("./res/aoc_day10_input.txt"))));
	}
	
	@Test
	public void testCalculateSumOfTiles() {
		List<List<Integer>> loopTestDataTwo = MazeSolver.findTheLoop(this.testDataTwo);
		Assertions.assertEquals(10, MazeSolver.calculateSumOfTiles(this.testDataTwo, loopTestDataTwo));
		
		List<List<Integer>> loopTestDataThree = MazeSolver.findTheLoop(this.testDataThree);
		Assertions.assertEquals(8, MazeSolver.calculateSumOfTiles(this.testDataThree, loopTestDataThree));
		
		List<String> realData = MazeSolver.importAllLines("./res/aoc_day10_input.txt");
		Assertions.assertEquals(443, MazeSolver.calculateSumOfTiles(realData, MazeSolver.findTheLoop(realData)));
	}
}
