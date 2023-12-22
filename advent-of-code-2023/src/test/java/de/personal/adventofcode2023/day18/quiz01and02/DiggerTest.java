package de.personal.adventofcode2023.day18.quiz01and02;

import java.awt.Point;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DiggerTest {
	private List<DigInstruction> testDigPlan;
	private List<DigInstruction> digPlan;
	
	@BeforeEach
	public void setup() {
		
	}
	
	@Disabled
	@Test
	public void testImport() {
		this.testDigPlan = Digger.importDigPlan("./res/aoc_day18_testinput.txt", false);
		testDigPlan.forEach(s -> System.out.println(s.getDirection() + " " + s.getNumberOfMoves()));
	}
	
	@Test
	public void testGetNumOfPointsInPolygonPartOne() {
		this.testDigPlan = Digger.importDigPlan("./res/aoc_day18_testinput.txt", true);
		this.digPlan = Digger.importDigPlan("./res/aoc_day18_input.txt", true);
		
		List<Point> testEdges = Digger.getEdges(this.testDigPlan);
		Assertions.assertEquals(62, Digger.getNumOfPointsInPolygon(testEdges));
		
		List<Point> edges = Digger.getEdges(this.digPlan);
		Assertions.assertEquals(40131, Digger.getNumOfPointsInPolygon(edges));
	}
	
	@Test
	public void testGetNumOfPointsInPolygonWithShoeLaceAlgorithmPartOne() {
		this.testDigPlan = Digger.importDigPlan("./res/aoc_day18_testinput.txt", true);
		this.digPlan = Digger.importDigPlan("./res/aoc_day18_input.txt", true);
		
		List<Point> testEdges = Digger.getEdges(this.testDigPlan);
		Assertions.assertEquals(62, Digger.getNumOfPointsInPolygonWithShoeLaceAlgorithm(testEdges));
		
		List<Point> edges = Digger.getEdges(this.digPlan);
		Assertions.assertEquals(40131, Digger.getNumOfPointsInPolygonWithShoeLaceAlgorithm(edges));
	}
	
	@Test
	public void testGetNumOfPointsInPolygonWithShoeLaceAlgorithmPartTwo() {
		this.testDigPlan = Digger.importDigPlan("./res/aoc_day18_testinput.txt", false);
		this.digPlan = Digger.importDigPlan("./res/aoc_day18_input.txt", false);

		List<Point> testEdges = Digger.getEdges(this.testDigPlan);
		Assertions.assertEquals(952408144115L, Digger.getNumOfPointsInPolygonWithShoeLaceAlgorithm(testEdges));

		/*
		 * Code does not compile for extremely large numbers, hence no solution to quiz 02.
		 */
		// List<Point> edges = Digger.getEdges(this.digPlan);
		// System.out.println(Digger.getNumOfPointsInPolygonWithShoeLaceAlgorithm(edges));
	}
}
