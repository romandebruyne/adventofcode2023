package de.personal.adventofcode2023.day03.quiz02;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GearRatioFinderTest {
	private GearRatioFinder grf;
	private List<String> testData;

	@BeforeEach
	public void setup() {
		System.out.println("PartNumberFinder test starts");
		this.grf = new GearRatioFinder();
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
		this.testData.add(".664.598..");
	}
	
	@Test
	@DisplayName("Calculate sum of gear ratios")
	public void testCalculateSumOfGearRatios() {
		this.grf.findAllGearPairs(this.testData);
		Assertions.assertEquals(467835, this.grf.calculateSumOfGearRatios(this.grf.getAllGears()));
		
		this.grf = new GearRatioFinder();
		this.grf.findAllGearPairs(this.grf.getAllLines());
		Assertions.assertEquals(75847567, this.grf.calculateSumOfGearRatios(this.grf.getAllGears()));
	}
}