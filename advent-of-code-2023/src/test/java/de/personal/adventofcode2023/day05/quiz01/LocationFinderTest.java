package de.personal.adventofcode2023.day05.quiz01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LocationFinderTest {
	
	private LocationFinder lf;
	
	@BeforeEach
	public void setup() {
		this.lf = new LocationFinder();
	}
	
	@Test
	@DisplayName("Find location with lowest value (test data)")
	public void testFindLocationWithLowestValue() {
		this.lf.importAllLines("./res/aoc_day05_testinput.txt");
		this.lf.transformLines(this.lf.getAllLines());
		this.lf.findLocations();
		Assertions.assertEquals(35, this.lf.findLocationWithLowestValue(this.lf.getSeedToLocationMap()));
	}
	
	@Test
	@DisplayName("FIND location with lowest value (real data")
	public void testFindLocationWithLowestValues() {
		this.lf.importAllLines("./res/aoc_day05_input.txt");
		this.lf.transformLines(this.lf.getAllLines());
		this.lf.findLocations();
		Assertions.assertEquals(424490994, this.lf.findLocationWithLowestValue(this.lf.getSeedToLocationMap()));
	}
}
