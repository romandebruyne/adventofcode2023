package de.personal.adventofcode2023.day14.quiz01;

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
		List<Character> col = RockFormationUtils.getColumnFromRockFormation(this.testData, 0);
		System.out.println(col);
		List<Character> tiltedCol = RockFormationUtils.tiltColumnToNorth(col);
		System.out.println(tiltedCol);
	}
	
	@Test
	public void testReplaceOriginalColumnWithTiltedColumn() {
		List<Character> col = RockFormationUtils.getColumnFromRockFormation(this.realData, 0);
		List<Character> tiltedCol = RockFormationUtils.tiltColumnToNorth(col);
		
		this.realData = RockFormationUtils.replaceOriginalColumnWithTiltedColumn(this.realData, tiltedCol, 0);
		
		this.realData.forEach(System.out::println);
	}
	
	@Test
	public void testTransformRockFormation() {
		RockFormationUtils.transformRockFormation(this.realData).forEach(System.out::println);
	}
	
	@Test
	public void testCalculateSum() {
		Assertions.assertEquals(136, RockFormationUtils.calculateSum(this.testData));
		Assertions.assertEquals(113424, RockFormationUtils.calculateSum(this.realData));
	}
}
