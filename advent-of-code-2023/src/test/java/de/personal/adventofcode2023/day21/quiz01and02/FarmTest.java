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
	public void testGetNumberOfGardenPlotsAfterXStepsPartOne() {
		Assertions.assertEquals(16, this.testFarm.getNumberOfGardenPlotsAfterXSteps(6));
		Assertions.assertEquals(3578, this.farm.getNumberOfGardenPlotsAfterXSteps(64));
	}
	
	@Test
	public void testGetNumberOfGardenPlotsAfterXStepsPartTwo() {
		List<Integer> maxSteps = Arrays.asList(6, 10, 50, 100);
		List<Long> solutions = Arrays.asList(16L, 50L, 1594L, 6536L);
		
		for (int i = 0; i < maxSteps.size(); i++) {
			Assertions.assertEquals(solutions.get(i), this.testFarm.getNumberOfGardenPlotsAfterXSteps(maxSteps.get(i)));
			this.testFarm = new Farm("./res/aoc_day21_testinput.txt", 11);
		}
	}
}
