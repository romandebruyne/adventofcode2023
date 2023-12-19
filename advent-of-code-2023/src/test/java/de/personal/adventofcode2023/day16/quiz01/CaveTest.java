package de.personal.adventofcode2023.day16.quiz01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaveTest {
	private Cave testCave;
	private Cave cave;
	
	@BeforeEach
	public void setup() {
		this.testCave = new Cave("./res/aoc_day16_testinput.txt", 10);
		this.cave = new Cave("./res/aoc_day16_input.txt", 110);
	}
	
	@Test
	public void testGetNumOfEnergizedTiles() {
		Assertions.assertEquals(46, this.testCave.getNumOfEnergizedTiles());
		Assertions.assertEquals(7307, this.cave.getNumOfEnergizedTiles());
	}
}
