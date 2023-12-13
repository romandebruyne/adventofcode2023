package de.personal.adventofcode2023.day12.quiz01and02;

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
		
//		List<Record> transformedRecordsTest = RecordUtils.transformAllRecords(allRecordsTest, 2);
//		List<Record> transformedRecords = RecordUtils.transformAllRecords(allRecords, 2);
//		transformedRecords.stream().map(s -> RecordUtils.getIndexesOfQuestionMarks(s.getParts()).size())
//			.collect(Collectors.toList()).forEach(System.out::println);
//		Assertions.assertEquals(525152, RecordUtils.calculateSumOfFeasiblePermutation(transformedRecordsTest));
	}
	
	@Test
	public void testTransformParts() {
		List<Record> allRecordsTest = RecordUtils.importAllData("./res/aoc_day12_testinput.txt");
		allRecordsTest.get(0).transformParts(5);
		allRecordsTest.get(0).transformSizes(5);
		
		System.out.println(allRecordsTest.get(0).getParts());
		System.out.println(allRecordsTest.get(0).getSizes());
	}
	
}
