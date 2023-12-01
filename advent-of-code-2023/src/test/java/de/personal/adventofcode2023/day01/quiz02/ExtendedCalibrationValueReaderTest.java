package de.personal.adventofcode2023.day01.quiz02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExtendedCalibrationValueReaderTest {
	
	@BeforeEach
	public void setup() {
		System.out.println("ExtendedCalibrationValueReader Test starts!");
	}
	
	@Test
	@DisplayName("Get the calibration value")
	public void testGetCalibratiomValue() {
		Assertions.assertEquals(55291, ExtendedCalibrationValueReader.getCalibrationValue());
	}
	
	@Test
	@DisplayName("Transform input string")
	public void testTransformInputString() {
		Assertions.assertEquals("219",
				ExtendedCalibrationValueReader.transformInputString("two1nine"));
		Assertions.assertEquals("823",
				ExtendedCalibrationValueReader.transformInputString("eightwothree"));
		Assertions.assertEquals("2134",
				ExtendedCalibrationValueReader.transformInputString("xtwone3four"));
	}
	
	@Test
	@DisplayName("Get two-digit value from string")
	public void testGetTwoDigitValue() {
		Assertions.assertEquals(29, ExtendedCalibrationValueReader.getTwoDigitValue(
				ExtendedCalibrationValueReader.transformInputString("two1nine")));
		Assertions.assertEquals(83, ExtendedCalibrationValueReader.getTwoDigitValue(
				ExtendedCalibrationValueReader.transformInputString("eightwothree")));
		Assertions.assertEquals(13, ExtendedCalibrationValueReader.getTwoDigitValue(
				ExtendedCalibrationValueReader.transformInputString("abcone2threexyz")));
		Assertions.assertEquals(24, ExtendedCalibrationValueReader.getTwoDigitValue(
				ExtendedCalibrationValueReader.transformInputString("xtwone3four")));
		Assertions.assertEquals(42, ExtendedCalibrationValueReader.getTwoDigitValue(
				ExtendedCalibrationValueReader.transformInputString("4nineeightseven2")));
		Assertions.assertEquals(14, ExtendedCalibrationValueReader.getTwoDigitValue(
				ExtendedCalibrationValueReader.transformInputString("zoneight234")));
		Assertions.assertEquals(76, ExtendedCalibrationValueReader.getTwoDigitValue(
				ExtendedCalibrationValueReader.transformInputString("7pqrstsixteen")));
		Assertions.assertEquals(74, ExtendedCalibrationValueReader.getTwoDigitValue(
				ExtendedCalibrationValueReader.transformInputString("7nine4")));
		Assertions.assertEquals(81, ExtendedCalibrationValueReader.getTwoDigitValue(
				ExtendedCalibrationValueReader.transformInputString("8451")));
		Assertions.assertEquals(79, ExtendedCalibrationValueReader.getTwoDigitValue(
				ExtendedCalibrationValueReader.transformInputString("sevenine")));
	}
}