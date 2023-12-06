package de.personal.adventofcode2023.day05.quiz01;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Day05Quiz01Test {
	
	private Almanac testAlm;
	private Almanac alm;
	String testInputPath = "./res/aoc_day05_testinput.txt";
	String realInputPath = "./res/aoc_day05_input.txt";
	
	@BeforeEach
	public void setup() {
		this.testAlm = new Almanac(testInputPath);
		this.alm = new Almanac(realInputPath);
	}
	
	@Test
	@DisplayName("Find location with lowest value")
	public void testFindLocationWithLowestValue() {
		List<Seed> allSeedsFromTestInput = AlmanacUtils.extractSeedsFromAllLines(this.testAlm);
		List<Seed> allSeedsFromRealInput = AlmanacUtils.extractSeedsFromAllLines(this.alm);
		
		for (Seed seed : allSeedsFromTestInput) {
			seed.determineLocationValue(this.testAlm);
		}
		
		for (Seed seed : allSeedsFromRealInput) {
			seed.determineLocationValue(this.alm);
		}
		
		Assertions.assertEquals(35, AlmanacUtils.findLocationWithLowestValue(allSeedsFromTestInput));
		Assertions.assertEquals(424490994, AlmanacUtils.findLocationWithLowestValue(allSeedsFromRealInput));
	}

}
