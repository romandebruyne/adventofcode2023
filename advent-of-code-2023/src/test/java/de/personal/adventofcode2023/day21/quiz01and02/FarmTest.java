package de.personal.adventofcode2023.day21.quiz01and02;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FarmTest {
	private Farm testFarm;
	private Farm farm;
	
	@BeforeEach
	public void setup() {
		this.testFarm = new Farm("./res/aoc_day21_testinput.txt", 11);
		this.farm = new Farm("./res/aoc_day21_input.txt", 131);
	}
	
	@Test
	public void testGetNumberOfGardenPlotsAfterXSteps() {
		Assertions.assertEquals(16, this.testFarm.getNumberOfGardenPlotsAfterXSteps(6, true));
		Assertions.assertEquals(3578, this.farm.getNumberOfGardenPlotsAfterXSteps(64, true));
	}
	
	@Test
	public void testGetNumberOfGardenPlotsAfterXStepsPartTwo() {
		System.out.println(this.testFarm.getNumberOfGardenPlotsAfterXSteps(100, false));
		
//		for (int maxStep : maxSteps) {
//			System.out.println(this.testFarm.getNumberOfGardenPlotsAfterXSteps(maxStep, false));
//			this.testFarm = new Farm("./res/aoc_day21_input.txt", 131);
//		}
	}
}
