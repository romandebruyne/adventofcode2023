package de.personal.adventofcode2023.day01.quiz02;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtendedCalibrationValueReader {
	private static String INPUT_PATH = "./res/aoc_day01_input.txt";
	
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
				try {
					if (i + 3 <= input.length() && input.substring(i, i + 3).equals("one")) {
						transformedInput += "1";
						i += 1;
					} else if (i + 3 <= input.length() && input.substring(i, i + 3).equals("two")) {
						transformedInput += "2";
						i += 1;
					} else if (i + 5 <= input.length() && input.substring(i, i + 5).equals("three")) {
						transformedInput += "3";
						i += 3;
					} else if (i + 4 <= input.length() && input.substring(i, i + 4).equals("four")) {
						transformedInput += "4";
						i += 2;
					} else if (i + 4 <= input.length() && input.substring(i, i + 4).equals("five")) {
						transformedInput += "5";
						i += 2;
					} else if (i + 3 <= input.length() && input.substring(i, i + 3).equals("six")) {
						transformedInput += "6";
						i += 1;
					} else if (i + 5 <= input.length() && input.substring(i, i + 5).equals("seven")) {
						transformedInput += "7";
						i += 3;
					} else if (i + 5 <= input.length() && input.substring(i, i + 5).equals("eight")) {
						transformedInput += "8";
						i += 3;
					} else if (i + 4 <= input.length() && input.substring(i, i + 4).equals("nine")) {
						transformedInput += "9";
						i += 2;
					}
					
				} catch (StringIndexOutOfBoundsException e) {
					System.err.println("Error occurred during String transformation.");
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
	
	private static boolean isNumeric(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}