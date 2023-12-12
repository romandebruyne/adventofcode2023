package de.personal.adventofcode2023.day12.quiz01;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class RecordUtilsTest {
	
	@Disabled
	@Test
	public void testImport() {
		RecordUtils.importAllData("./res/aoc_day12_input.txt").forEach(System.out::println);
	}
	
	@Test
	public void testCalculateSumOfFeasiblePermutations() {
		List<Record> allRecordsTest = RecordUtils.importAllData("./res/aoc_day12_testinput.txt");
		Assertions.assertEquals(21, RecordUtils.calculateSumOfFeasiblePermutation(allRecordsTest));
		
		List<Record> allRecords = RecordUtils.importAllData("./res/aoc_day12_input.txt");
		Assertions.assertEquals(7771, RecordUtils.calculateSumOfFeasiblePermutation(allRecords));
	}
}
