package de.personal.adventofcode2023.day03.quiz01;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PartNumberFinderTest {
	
	private PartNumberFinder pnf;
	private List<String> testData;

	@BeforeEach
	public void setup() {
		System.out.println("PartNumberFinder test starts");
		this.pnf = new PartNumberFinder();
		this.testData = new ArrayList<>();
		this.testData.add("467..114..");
		this.testData.add("...*......");
		this.testData.add("..35..633.");
		this.testData.add("......#...");
		this.testData.add("617*......");
		this.testData.add(".....+.58.");
		this.testData.add("..592.....");
		this.testData.add("......755.");
		this.testData.add("...$.*....");
		this.testData.add(".664.598..)");
	}
	
	@Test
	@DisplayName("Calculate sum of part numbers")
	public void testCalculateSumOfAllPartNumbers() {
		System.out.println(this.pnf.calculateSumOfAllPartNumbers(testData));
	}
	
	@Test
	@DisplayName("Reverse order of number formatted string")
	public void testReverseOrderOfNumberFormattedString() {
		Assertions.assertEquals(754, this.pnf.reverseOrderInNumberFormattedString("457"));
	}
}
