package de.personal.adventofcode2023.day13.quiz01;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatternUtilsTest {
	
	@Test
	public void testImportAllPatterns() {
		List<Pattern> allPatterns = PatternUtils.importAllPatterns("./res/aoc_day13_input.txt");
		Assertions.assertEquals(100, allPatterns.size());
	}
	
	@Test
	public void testCalculateSum() {
		List<Pattern> allPatterns = PatternUtils.importAllPatterns("./res/aoc_day13_input.txt");
		Assertions.assertEquals(35360, PatternUtils.calculateSum(allPatterns));
		
	}
}
