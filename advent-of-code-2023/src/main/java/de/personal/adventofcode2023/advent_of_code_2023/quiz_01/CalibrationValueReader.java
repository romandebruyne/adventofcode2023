package de.personal.adventofcode2023.advent_of_code_2023.quiz_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CalibrationValueReader {
	
	private static String INPUT_PATH = "./res/aoc_quiz_01_input.txt";
	
	private CalibrationValueReader() {
		
	}
	
	public static int getCalibrationValue() {
		String line;
		String[] lineArray;
		List<Integer> numbers = new ArrayList<>();
		int sum = 0;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(INPUT_PATH)) ) {
			
			while ((line = br.readLine()) != null) {
				lineArray = line.split("");
				
				for (int i = 0; i < lineArray.length; i++) {
					if (isNumeric(lineArray[i])) {
						numbers.add(Integer.parseInt(lineArray[i]));
					}
				}
				
				if (!numbers.isEmpty()) {
					sum += numbers.get(0);
					sum += numbers.get(numbers.size() - 1);
				}
				
				numbers.clear();					
			}
			
		} catch (IOException e) {
			System.err.println("An error occurred during the input import.");
		}
		
		return sum;
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
