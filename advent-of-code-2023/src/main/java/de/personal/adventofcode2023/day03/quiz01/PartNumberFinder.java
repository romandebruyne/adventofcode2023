package de.personal.adventofcode2023.day03.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PartNumberFinder {
	private final static String INPUT_PATH = "./res/aoc_day03_input.txt";
	private final static String ALL_SYMBOLS = "!\"#$%&'()*+-,=/@";
	private final List<String> allLines = new ArrayList<>();
	
	public PartNumberFinder() {
		importData();
	}
	
	public void importData() {
		String line;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(INPUT_PATH))) {
			while ((line = br.readLine()) != null) {
				this.allLines.add(line);
			}
			
		} catch (IOException e) {
			System.err.println("Error occurred during data import.");
		}
	}
	
	public int calculateSumOfAllPartNumbers(List<String> allLines) {
		int symbolPosition, sumOfPartNumbers = 0;

		for (int lineIndex = 0; lineIndex < allLines.size(); lineIndex++) {
			for (int i = 0; i < allLines.get(lineIndex).length(); i++) {
				if (isSymbol(allLines.get(lineIndex).charAt(i))) {
					symbolPosition = i;

					for (String line : allLines) {
						System.out.println(line);
						sumOfPartNumbers += getPartNumberBeforeSymbol(line, symbolPosition);
						sumOfPartNumbers += getPartNumberAfterSymbol(line, symbolPosition);
					}
				}
			}
		}
		
		return sumOfPartNumbers;
	}
	
	public int getPartNumberBeforeSymbol(String input, int symbolPosition) {
		String partNumberAsString = "";
		
		for (int i = symbolPosition - 1; i >= 0; i--) {
			if (isNumeric(input.charAt(i))) {
				partNumberAsString += input.charAt(i);
			} else if (isSymbol(input.charAt(i)) || input.charAt(i) == '.') {
				break;
			}
		}
		return reverseOrderInNumberFormattedString(partNumberAsString);
	}
	
	public int getPartNumberAfterSymbol(String input, int symbolPosition) {
		String partNumberAsString = "";
		
		for (int i = symbolPosition; i < input.length(); i++) {
			if (isNumeric(input.charAt(i))) {
				partNumberAsString += input.charAt(i);
			} else if (isSymbol(input.charAt(i)) || input.charAt(i) == '.') {
				break;
			}
		}
		
		try {
			return Integer.parseInt(partNumberAsString);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public int reverseOrderInNumberFormattedString(String input) {
		String reversedInput = "";

		try {
			Integer.parseInt(input);
			for (int i = input.length() - 1; i >= 0; i--) {
				reversedInput += input.charAt(i);
			}
		} catch (NumberFormatException e) {
			return 0;
		}
		
		return Integer.parseInt(reversedInput);			
	}
	
	private boolean isSymbol(char input) {
		for (int i = 0; i < ALL_SYMBOLS.length(); i++) {
			if (input == ALL_SYMBOLS.charAt(i)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isNumeric(char input) {
		return Character.isDigit(input);
	}
}