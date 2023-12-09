package de.personal.adventofcode2023.day09.quiz01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	}
	
	@Test
	public void testPredictNextValueOfSequence() {
		for (List<Long> seq : this.testData) {
			System.out.println(SequenceSolver.predictNextValueOfSequence(SequenceSolver.findZeroSequence(seq)));
		}
		
		// what if input seq is all negative?
		System.out.println(SequenceSolver.predictNextValueOfSequence(
				SequenceSolver.findZeroSequence(
						new ArrayList<>(Arrays.asList(-2L, -3L, -4L, -5L, -6L, -7L, -8L, -9L, -10L,
								-11L, -12L, -13L, -14L, -15L, -16L, -17L, -18L, -19L, -20L, -21L, -22L)))));
	}
	
	@Test
	public void testCalculateSumOfPredictedValues() {
		Assertions.assertEquals(114, SequenceSolver.calculateSumOfPredictedValues(this.testData));
		Assertions.assertEquals(1938800261, SequenceSolver.calculateSumOfPredictedValues(
				SequenceSolver.importAllSequences("./res/aoc_day09_input.txt")));
	}
}
