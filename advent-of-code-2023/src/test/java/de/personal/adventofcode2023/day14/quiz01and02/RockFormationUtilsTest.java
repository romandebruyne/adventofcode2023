package de.personal.adventofcode2023.day14.quiz01and02;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class RockFormationUtilsTest {
	
	private List<String> testData;
	private List<String> realData;
	
	@BeforeEach
	public void setup() {
		this.testData = RockFormationUtils.importRockFormation("./res/aoc_day14_testinput.txt");
		this.realData = RockFormationUtils.importRockFormation("./res/aoc_day14_input.txt");
	}
	
	@Disabled
	@Test
	public void testImport() {
		Assertions.assertEquals(1000, this.realData.size());
	}
	
	@Test
	public void testGetColumnFromRockFormation() {
		System.out.println(RockFormationUtils.getColumnFromRockFormation(this.realData, 0));
	}
	
	@Test
	public void testTiltColumnToNorth() {
		List<Character> col = RockFormationUtils.getColumnFromRockFormation(this.realData, 8);
		System.out.println(col);
		List<Character> tiltedCol = RockFormationUtils.tiltColumnToNorth(col);
		System.out.println(tiltedCol);
	}
	
	@Test
	public void testTiltColumnToSouth() {
		List<Character> col = RockFormationUtils.getColumnFromRockFormation(this.realData, 8);
		System.out.println(col);
		List<Character> tiltedCol = RockFormationUtils.tiltColumnToSouth(col);
		System.out.println(tiltedCol);
	}
	
	@Test
	public void testTiltRowToWest() {
		List<Character> row = RockFormationUtils.convertStringToCharList(this.realData.get(8));
		System.out.println(row);
		List<Character> tiltedRow = RockFormationUtils.tiltRowToWest(row);
		System.out.println(tiltedRow);
	}
	
	@Test
	public void testTiltRowToEast() {
		List<Character> row = RockFormationUtils.convertStringToCharList(this.realData.get(8));
		System.out.println(row);
		List<Character> tiltedRow = RockFormationUtils.tiltRowToEast(row);
		System.out.println(tiltedRow);
	}
	
	@Test
	public void testReplaceOriginalColumnWithTiltedColumn() {
		List<Character> col = RockFormationUtils.getColumnFromRockFormation(this.realData, 0);
		List<Character> tiltedCol = RockFormationUtils.tiltColumnToNorth(col);
		this.realData = RockFormationUtils.replaceOriginalColumnWithTiltedColumn(this.realData, tiltedCol, 0);
		this.realData.forEach(System.out::println);
	}
	
	@Test
	public void testTiltRockFormationToNorth() {
		RockFormationUtils.tiltRockFormationToNorth(this.realData).forEach(System.out::println);
	}
	
	@Test
	public void testCalculateSum() {
		Assertions.assertEquals(136, RockFormationUtils.calculateSum(
				RockFormationUtils.tiltRockFormationToNorth(this.testData)));
		Assertions.assertEquals(113424, RockFormationUtils.calculateSum(
				RockFormationUtils.tiltRockFormationToNorth(this.realData)));
	}
	
	@Test
	public void testCalculateSumAfterNCycles() {
		Assertions.assertEquals(64, RockFormationUtils.calculateSumAfterNCycles(this.testData, 1000000000));
		Assertions.assertEquals(96003, RockFormationUtils.calculateSumAfterNCycles(this.realData, 1000000000));
		
	}
	
	@Test
	public void testCycleRockFormation() {
		RockFormationUtils.cycleRockFormation(this.testData).forEach(System.out::println);;
	}
}
