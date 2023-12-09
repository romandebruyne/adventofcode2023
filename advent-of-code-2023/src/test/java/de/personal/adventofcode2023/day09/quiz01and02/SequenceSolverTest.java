package de.personal.adventofcode2023.day09.quiz01and02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.personal.adventofcode2023.day09.quiz01and02.SequenceSolver;

public class SequenceSolverTest {
	private List<List<Long>> testData;
	
	@BeforeEach
	public void setup() {
		this.testData = new ArrayList<>();
		this.testData.add(new ArrayList<>(Arrays.asList(0L, 3L, 6L, 9L, 12L, 15L)));
		this.testData.add(new ArrayList<>(Arrays.asList(1L, 3L, 6L, 10L, 15L, 21L)));
		this.testData.add(new ArrayList<>(Arrays.asList(10L, 13L, 16L, 21L, 30L, 45L)));
	}
	
	@Test
	public void testImportAllSequences() {
		System.out.println(SequenceSolver.importAllSequences("./res/aoc_day09_input.txt").size());
	}
	
	@Test
	public void testFindZeroSequence() {
		for (List<Long> seq : this.testData) {
			SequenceSolver.findZeroSequence(seq).forEach(s -> System.out.println(s));
		}
		
		for (List<Long> seq : this.testData) {
			SequenceSolver.findZeroSequence(seq).forEach(s -> System.out.println(s));
		}
	}
	
	@Test
	public void testExtrapolation() {
		for (List<Long> seq : this.testData) {
			System.out.println(SequenceSolver.findValueWithForwardExtrapolation(SequenceSolver.findZeroSequence(seq)));
		}
		
		for (List<Long> seq : this.testData) {
			System.out.println(SequenceSolver.findValueWithBackwardExtrapolation(SequenceSolver.findZeroSequence(seq)));
		}
	}
	
	@Test
	public void testCalculateSumOfPredictedValues() {
		Assertions.assertEquals(114, SequenceSolver.calculateSumOfPredictedValues(this.testData, true));
		Assertions.assertEquals(1938800261, SequenceSolver.calculateSumOfPredictedValues(
				SequenceSolver.importAllSequences("./res/aoc_day09_input.txt"), true));
		
		Assertions.assertEquals(2, SequenceSolver.calculateSumOfPredictedValues(this.testData, false));
		Assertions.assertEquals(1112, SequenceSolver.calculateSumOfPredictedValues(
				SequenceSolver.importAllSequences("./res/aoc_day09_input.txt"), false));
	}
}
