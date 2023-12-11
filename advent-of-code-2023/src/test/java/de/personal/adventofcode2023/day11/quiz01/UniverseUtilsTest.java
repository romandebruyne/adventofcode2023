package de.personal.adventofcode2023.day11.quiz01;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class UniverseUtilsTest {
	
	private List<String> testData;
	private List<String> testDataExpanded;
	
	@BeforeEach
	public void setup() {
		this.testData = UniverseUtils.importUniverse("./res/aoc_day11_testinput.txt");
		this.testDataExpanded = UniverseUtils.importUniverse("./res/aoc_day11_testinput_expanded.txt");
	}
	
	@Disabled
	@Test
	public void testImportUniverse() {
		this.testData.forEach(System.out::println);
	}
	
	@Test
	public void testExpandUniverse() {
		List<String> expandedUniverse = UniverseUtils.expandUniverse(this.testData);
		Assertions.assertTrue(expandedUniverse.equals(this.testDataExpanded));
	}
	
	@Test
	public void testGetColumnsIndexesWithNoGalaxy() {
		List<Integer> colIndexes = UniverseUtils.getColumnsIndexesWithNoGalaxy(
				UniverseUtils.importUniverse("./res/aoc_day11_input.txt"));
		Assertions.assertEquals(5, colIndexes.size());
	}
	
	@Test
	public void testFindCoordinatesOfGalaxy() {
		List<String> expandedUniverseTest = UniverseUtils.expandUniverse(this.testData);
		Map<Integer, List<Integer>> coordsTest = UniverseUtils.findCoordinatesOfGalaxy(expandedUniverseTest);
		Assertions.assertEquals(9, coordsTest.size());
		
		List<String> expandedUniverseReal = UniverseUtils.expandUniverse(
				UniverseUtils.importUniverse("./res/aoc_day11_input.txt"));
		Map<Integer, List<Integer>> coordsReal = UniverseUtils.findCoordinatesOfGalaxy(expandedUniverseReal);
		Assertions.assertEquals(450, coordsReal.size());
	}
	
	@Test
	public void testCalculateSumOfDistances() {
		Assertions.assertEquals(374, UniverseUtils.calculateSumOfDistances(this.testData));
		
		List<String> universe = UniverseUtils.importUniverse("./res/aoc_day11_input.txt");
		Assertions.assertEquals(10494813, UniverseUtils.calculateSumOfDistances(universe));
	}
}
