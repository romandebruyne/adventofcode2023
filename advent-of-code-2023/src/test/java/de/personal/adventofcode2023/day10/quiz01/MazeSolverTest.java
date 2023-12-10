package de.personal.adventofcode2023.day10.quiz01;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeSolverTest {
	
	private List<String> testData;
	
	@BeforeEach
	public void setup() {
		this.testData = MazeSolver.importAllLines("./res/aoc_day10_testinput.txt");
	}
	
	@Test
	public void testDetermineShapeOfS() {
		List<Integer> startingPosition = MazeSolver.findStartingPosition(this.testData);
		Assertions.assertEquals('F', MazeSolver.determineShapeOfS(this.testData, startingPosition));
		
		List<String> realData = MazeSolver.importAllLines("./res/aoc_day10_input.txt");
		startingPosition = MazeSolver.findStartingPosition(realData);
		Assertions.assertEquals('7', MazeSolver.determineShapeOfS(realData, startingPosition));
	}
	
	@Test
	public void testDetermineNextPipe() {
		List<Integer> startingPosition = MazeSolver.findStartingPosition(this.testData);
		List<Integer> nextPipePosition = startingPosition;
		List<Integer> previousPipePosition = new ArrayList<>();
		
		this.testData.set(startingPosition.get(0),
				this.testData.get(startingPosition.get(0)).replace('S', 
						MazeSolver.determineShapeOfS(this.testData, startingPosition)));
		
		nextPipePosition = MazeSolver.determineNextPipe(this.testData, nextPipePosition, previousPipePosition);
	}
	
	@Test
	public void testFindTheWayThroughTheMaze() {
		Assertions.assertEquals(4, MazeSolver.determineMaximumSteps(this.testData));
		Assertions.assertEquals(6979,
				MazeSolver.determineMaximumSteps(MazeSolver.importAllLines("./res/aoc_day10_input.txt")));
	}
	

}
