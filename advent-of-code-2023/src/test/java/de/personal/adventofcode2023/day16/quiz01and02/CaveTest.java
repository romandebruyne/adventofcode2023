package de.personal.adventofcode2023.day16.quiz01and02;

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
		Assertions.assertEquals(46, this.testCave.getNumOfEnergizedTiles(new Beam(0, -1, 'R')));
		Assertions.assertEquals(7307, this.cave.getNumOfEnergizedTiles(new Beam(0, -1, 'R')));
	}
	
	@Test
	public void testGetHighestNumOfEnergizedTiles() {
		Assertions.assertEquals(51, this.testCave.getHighestNumOfEnergizedTiles());
		Assertions.assertEquals(7635, this.cave.getHighestNumOfEnergizedTiles());
	}
}