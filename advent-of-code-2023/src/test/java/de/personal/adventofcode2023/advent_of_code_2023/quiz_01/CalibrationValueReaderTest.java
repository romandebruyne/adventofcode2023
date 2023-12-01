package de.personal.adventofcode2023.advent_of_code_2023.quiz_01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalibrationValueReaderTest {
	
	@BeforeEach
	public void setup() {
		System.out.println("CalibrationValueReader Test starts!");
	}
	
	@Test
	@DisplayName("Get the calibration value")
	public void testGetCalibratiomValue() {
		Assertions.assertEquals(55607, CalibrationValueReader.getCalibrationValue());
	}
	
	@Test
	@DisplayName("Get numeric value from string")
	public void testGetNumericValue() {
		Assertions.assertEquals(12, CalibrationValueReader.getNumericValue("1abc2"));
		Assertions.assertEquals(38, CalibrationValueReader.getNumericValue("pqr3stu8vwx"));
		Assertions.assertEquals(15, CalibrationValueReader.getNumericValue("a1b2c3d4e5f"));
		Assertions.assertEquals(77, CalibrationValueReader.getNumericValue("treb7uchet"));
	}
	
	@Test
	public void testSomething() {
		String one = "1";
		String two = "2";
		System.out.println(one + two);
	}
	
}
