package de.personal.adventofcode2023.day13.quiz01;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PatternUtilsTest {
	
	private List<Pattern> testData;
	private List<Pattern> realData;
	
	@BeforeEach
	public void setup() {
		this.testData = PatternUtils.importAllPatterns("./res/aoc_day13_testinput.txt", 15);
		this.realData = PatternUtils.importAllPatterns("./res/aoc_day13_input.txt", 1407);
	}
	
	@Test
	public void testImportAllPatterns() {
		Assertions.assertEquals(2, this.testData.size());
		Assertions.assertEquals(100, this.realData.size());
	}
	
	@Test
	public void testCalculateSum() {
		Assertions.assertEquals(405, PatternUtils.calculateSum(this.testData));
		Assertions.assertEquals(35360, PatternUtils.calculateSum(this.realData));
		
	}
	
	@Test
	public void testCalculateSumBasedOnTransformedPatterns() {
		Assertions.assertEquals(400, PatternUtils.calculateSumBasedOnTransformedPatterns(this.testData));
		Assertions.assertEquals(36755, PatternUtils.calculateSumBasedOnTransformedPatterns(this.realData));
	}
}
