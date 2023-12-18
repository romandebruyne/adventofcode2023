package de.personal.adventofcode2023.day18.quiz01;

import java.awt.Point;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiggerTest {
	private List<DigInstruction> testDigPlan;
	private List<DigInstruction> digPlan;
	
	@BeforeEach
	public void setup() {
		this.testDigPlan = Digger.importDigPlan("./res/aoc_day18_testinput.txt");
		this.digPlan = Digger.importDigPlan("./res/aoc_day18_input.txt");
	}
	
	@Test
	public void testGetNumOfPointsInPolygon() {
		List<Point> testEdges = Digger.digEdges(this.testDigPlan);
		Assertions.assertEquals(62, Digger.getNumOfPointsInPolygon(testEdges));
		
		List<Point> edges = Digger.digEdges(this.digPlan);
		Assertions.assertEquals(40131, Digger.getNumOfPointsInPolygon(edges));
	}
}
