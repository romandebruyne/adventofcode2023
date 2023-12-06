package de.personal.adventofcode2023.day05.quiz01and02;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Day05Test {
	
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
	
	@Test
	@DisplayName("Find location with lowest value from seed range")
	public void testFindLocationWithLowestValueFromSeedRange() {
		List<Seed> allSeedsFromTestInput = AlmanacUtils.extractSeedsFromAllLines(this.testAlm);
		List<Seed> allSeedsFromRealInput = AlmanacUtils.extractSeedsFromAllLines(this.alm);
		
		List<Seed> allSeedsAndRangeFromTestInput = AlmanacUtils.transformSeedList(allSeedsFromTestInput);
		List<Seed> allSeedsAndRangeFromRealInput = AlmanacUtils.transformSeedList(allSeedsFromRealInput);
		
		Assertions.assertEquals(46, AlmanacUtils.findLocationWithLowestValueFromSeedRange(
				allSeedsAndRangeFromTestInput, this.testAlm));
		Assertions.assertEquals(15290096, AlmanacUtils.findLocationWithLowestValueFromSeedRange(
				allSeedsAndRangeFromRealInput, this.alm));
	}
}
