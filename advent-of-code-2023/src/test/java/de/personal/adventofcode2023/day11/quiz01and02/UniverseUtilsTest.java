package de.personal.adventofcode2023.day11.quiz01and02;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class UniverseUtilsTest {
	
	private List<String> testData;
	
	@BeforeEach
	public void setup() {
		this.testData = UniverseUtils.importUniverse("./res/aoc_day11_testinput.txt");
	}
	
	@Disabled
	@Test
	public void testImportUniverse() {
		this.testData.forEach(System.out::println);
	}
	
	@Test
	public void testGetColumnsIndexesWithNoGalaxy() {
		List<Integer> colIndexes = UniverseUtils.getColumnsIndexesWithNoGalaxy(
				UniverseUtils.importUniverse("./res/aoc_day11_input.txt"));
		Assertions.assertEquals(5, colIndexes.size());
	}
	
	@Test
	public void testFindCoordinatesOfGalaxy() {
		List<String> expandedUniverseTest = UniverseUtils.expandUniverse(this.testData, 2);
		Map<Integer, List<Integer>> coordsTest = UniverseUtils.findCoordinatesOfGalaxy(expandedUniverseTest, 2);
		Assertions.assertEquals(9, coordsTest.size());
		
		List<String> expandedUniverseReal = UniverseUtils.expandUniverse(
				UniverseUtils.importUniverse("./res/aoc_day11_input.txt"), 2);
		Map<Integer, List<Integer>> coordsReal = UniverseUtils.findCoordinatesOfGalaxy(expandedUniverseReal, 2);
		Assertions.assertEquals(450, coordsReal.size());
	}
	
	@Test
	public void testCalculateSumOfDistances() {
		Assertions.assertEquals(374, UniverseUtils.calculateSumOfDistances(this.testData, 2).longValue());
		Assertions.assertEquals(1030, UniverseUtils.calculateSumOfDistances(this.testData, 10).longValue());
		Assertions.assertEquals(8410, UniverseUtils.calculateSumOfDistances(this.testData, 100).longValue());
		
		List<String> universe = UniverseUtils.importUniverse("./res/aoc_day11_input.txt");
		Assertions.assertEquals(10494813, UniverseUtils.calculateSumOfDistances(universe, 2).longValue());
		Assertions.assertEquals(840988812853L, UniverseUtils.calculateSumOfDistances(universe, 1000000).longValue());
		
		
	}
}
