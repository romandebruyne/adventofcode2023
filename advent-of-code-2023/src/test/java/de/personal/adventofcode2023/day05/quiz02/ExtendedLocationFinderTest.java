package de.personal.adventofcode2023.day05.quiz02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExtendedLocationFinderTest {
	
	private ExtendedLocationFinder elf;
	
	@BeforeEach
	public void setup() {
		this.elf = new ExtendedLocationFinder();
	}
	
	@Test
	@DisplayName("Import and transform data")
	public void testImportAndTransfromData() {
		this.elf.importAllLines("./res/aoc_day05_testinput.txt");
		this.elf.transformLines(this.elf.getAllLines());
		Assertions.assertEquals(27, this.elf.getAllSeeds().size());
	}
	
	@Test
	@DisplayName("Find location with lowest value (test data)")
	public void testFindLocationWithLowestValueTestData() {
		this.elf.importAllLines("./res/aoc_day05_testinput.txt");
		this.elf.transformLines(this.elf.getAllLines());
		this.elf.findLocations();
		Assertions.assertEquals(46, this.elf.findLocationWithLowestValue(this.elf.getSeedToLocationMap()));
	}
}