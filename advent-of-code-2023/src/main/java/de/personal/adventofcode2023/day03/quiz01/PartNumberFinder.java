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
		int partNumber, sumOfPartNumbers = 0;
		List<Integer> numberIndexPositions = new ArrayList<>();

		for (int lineIndex = 0; lineIndex < allLines.size(); lineIndex++) {
			for (int i = 0; i < allLines.get(lineIndex).length(); i++) {
				partNumber = getPartNumber(allLines.get(lineIndex), i);
				numberIndexPositions = getPartNumberIndexPositions(allLines.get(lineIndex), i);
				
				if (isAdjacentToSymbol(allLines, lineIndex, numberIndexPositions)) {
					sumOfPartNumbers += partNumber;
				}
				
				i += numberIndexPositions.size();
			}
		}
		
		return sumOfPartNumbers;
	}
	
	public int getPartNumber(String input, int startPosition) {
		String partNumberAsString = "";
		
		for (int i = startPosition; i < input.length(); i++) {
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
	
	public List<Integer> getPartNumberIndexPositions(String input, int startPosition) {
		List<Integer> positions = new ArrayList<>();
		
		for (int i = startPosition; i < input.length(); i++) {
			if (isNumeric(input.charAt(i))) {
				positions.add(i);
			} else if (isSymbol(input.charAt(i)) || input.charAt(i) == '.') {
				return positions;
			}
		}
		
		return positions;
	}
	
	public boolean isAdjacentToSymbol(List<String> allLines, int lineIndex, List<Integer> numberIndexPositions) {
		for (int position : numberIndexPositions) {
			if (lineIndex > 0) {
				if (position == 0) {
					if (isSymbol(allLines.get(lineIndex - 1).charAt(position)) ||
							isSymbol(allLines.get(lineIndex - 1).charAt(position + 1))) {
						return true;
					}
				} else if (position == allLines.get(lineIndex).length() - 1) {
					if (isSymbol(allLines.get(lineIndex - 1).charAt(position - 1)) || 
						isSymbol(allLines.get(lineIndex - 1).charAt(position))) {
						return true;
					}
				} else {
					if (isSymbol(allLines.get(lineIndex - 1).charAt(position - 1)) || 
							isSymbol(allLines.get(lineIndex - 1).charAt(position)) ||
							isSymbol(allLines.get(lineIndex - 1).charAt(position + 1))) {
						return true;
					}
				}
			}
			
			if (lineIndex < allLines.size() - 1) {
				if (position == 0) {
					if (isSymbol(allLines.get(lineIndex + 1).charAt(position)) ||
							isSymbol(allLines.get(lineIndex + 1).charAt(position + 1))) {
						return true;
					}
				} else if (position == allLines.get(lineIndex + 1).length() - 1) {
					if (isSymbol(allLines.get(lineIndex + 1).charAt(position - 1)) || 
						isSymbol(allLines.get(lineIndex + 1).charAt(position))) {
						return true;
					}
				} else {
					if (isSymbol(allLines.get(lineIndex + 1).charAt(position - 1)) || 
							isSymbol(allLines.get(lineIndex + 1).charAt(position)) ||
							isSymbol(allLines.get(lineIndex + 1).charAt(position + 1))) {
						return true;
					}
				}
			}
			
			if (position == 0) {
				if (isSymbol(allLines.get(lineIndex).charAt(position + 1))) {
					return true;
				}
			} else if (position == allLines.get(lineIndex).length() - 1) {
				if (isSymbol(allLines.get(lineIndex).charAt(position - 1))) {
					return true;
				}
			} else {
				if (isSymbol(allLines.get(lineIndex).charAt(position - 1)) || 
						isSymbol(allLines.get(lineIndex).charAt(position + 1))) {
					return true;
				}
			}
		}
		
		return false;
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
	
	public List<String> getAllLines() {
		return this.allLines;
	}
}