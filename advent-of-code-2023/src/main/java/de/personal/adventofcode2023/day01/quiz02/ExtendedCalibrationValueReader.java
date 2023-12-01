package de.personal.adventofcode2023.day01.quiz02;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ExtendedCalibrationValueReader {
	private static String INPUT_PATH = "./res/aoc_day01_input.txt";
	private static Map<String, String> NUMBER_MAPPING = createNumberMapping();
	
	private ExtendedCalibrationValueReader() {}
	
	public static int getCalibrationValue() {
		String line;
		int sum = 0;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(INPUT_PATH)) ) {
			while ((line = br.readLine()) != null) {
				sum += getTwoDigitValue(transformInputString(line));
			}
			
		} catch (IOException e) {
			System.err.println("An error occurred during the input import.");
		}
		
		return sum;
	}
	
	public static String transformInputString(String input) {
		String transformedInput = "";
		
		for (int i = 0; i < input.length(); i++) {
			if (isNumeric(input.substring(i, i + 1))) {
				transformedInput += input.substring(i, i + 1);
			} else {
				for (String key : NUMBER_MAPPING.keySet()) {
					try {
						if (i + key.length() <= input.length() &&
								input.substring(i, i + key.length()).equals(key)) {
							transformedInput += NUMBER_MAPPING.get(key);
							i += key.length() - 2;
						}
						
					} catch (StringIndexOutOfBoundsException e) {
						System.err.println("Error occurred during String transformation.");
					}
				}
			}
		}
		return transformedInput;
	}
	
	public static int getTwoDigitValue(String input) {
		String[] inputArray = input.split("");

		if (!input.isEmpty()) {
			return Integer.parseInt(inputArray[0] + inputArray[inputArray.length - 1]);
		} else {
			return 0;
		}
	}
	
	private static Map<String, String> createNumberMapping() {
		Map<String, String> mapping = new HashMap<>();
		mapping.put("one", "1");
		mapping.put("two", "2");
		mapping.put("three", "3");
		mapping.put("four", "4");
		mapping.put("five", "5");
		mapping.put("six", "6");
		mapping.put("seven", "7");
		mapping.put("eight", "8");
		mapping.put("nine", "9");
		return mapping;
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