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
	@DisplayName("Find all gears")
	public void testFindAllGears() {
		this.grf.findAllGears(this.testData);
	}
	
	@Test
	@DisplayName("Calculate sum of gear ratios")
	public void testCalculateSumOfGearRatios() {
		this.grf.findAllGears(this.testData);
		Assertions.assertEquals(467835, this.grf.calculateSumOfGearRatios(this.grf.getAllGears()));
	}
	
	@Test
	@DisplayName("Calculate sum of gear ratios")
	public void testCalculateSumOfGearRatioss() {
		this.grf.findAllGears(this.grf.getAllLines());
		System.out.println(this.grf.calculateSumOfGearRatios(this.grf.getAllGears()));
		// Assertions.assertEquals(467835, this.grf.calculateSumOfGearRatios(this.grf.getAllGears()));
	}
}
