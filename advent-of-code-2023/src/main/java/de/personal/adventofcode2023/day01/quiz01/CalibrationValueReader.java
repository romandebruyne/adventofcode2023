package de.personal.adventofcode2023.day01.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CalibrationValueReader {
	private static String INPUT_PATH = "./res/aoc_day01_input.txt";
	
	private CalibrationValueReader() {}
	
	public static int getCalibrationValue() {
		String line;
		int sum = 0;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(INPUT_PATH)) ) {
			
			while ((line = br.readLine()) != null) {
				sum += getNumericValue(line);
			}
			
		} catch (IOException e) {
			System.err.println("An error occurred during the input import.");
		}
		
		return sum;
	}
	
	public static int getNumericValue(String input) {
		int numericValue;
		String[] inputArray = input.split("");
		List<String> values = new ArrayList<>();
		
		for (int i = 0; i < inputArray.length; i++) {
			if (isNumeric(inputArray[i])) {
				values.add(inputArray[i]);
			}
		}
		
		if (!values.isEmpty()) {
			numericValue = Integer.parseInt(values.get(0) + values.get(values.size() - 1));
		} else {
			numericValue = 0;
		}
		
		return numericValue;
	}
	
	private static boolean isNumeric(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
