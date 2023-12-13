package de.personal.adventofcode2023.day13.quiz01and02;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatternUtils {
	
	private PatternUtils() {}
	
	public static List<Pattern> importAllPatterns(String inputPath, int maxRowIndex) {
		List<String> patternContent = new ArrayList<>();
		List<Pattern> allPatterns = new ArrayList<>();
		String line;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			for (int i = 0; i < maxRowIndex + 1; i++) {
				line = br.readLine();
				
				if (line == null || line.isEmpty()) {
					allPatterns.add(new Pattern(patternContent));
					patternContent = new ArrayList<>();
				} else if (line != null) {
					patternContent.add(line);
				}
				
			}
			
		} catch (IOException e) {
			System.err.println("Error during data import.");
		}
		
		return allPatterns;
	}
	
	public static long calculateSum(List<Pattern> allPatterns) {
		long colValue, rowValue, sum = 0;
		
		for (Pattern pat : allPatterns) {
			colValue = determinePositionOfVerticalMirror(pat);
			rowValue = determinePositionOfHorizontalMirror(pat);
			
			if (colValue >= 0) {
				sum += (colValue + 1);
				pat.setColumnIndex(colValue);
			}
			
			if (rowValue >= 0) {
				sum += (rowValue + 1) * 100;
				pat.setRowValue(rowValue);
			}
		}
		
		return sum;
	}
	
	public static long calculateSumBasedOnTransformedPatterns(List<Pattern> allPatterns) {
		long sum = 0;
		
		// To determine indexes of mirrors in non-transformed patterns
		calculateSum(allPatterns);
		
		for (Pattern pat : allPatterns) {
			sum += getValueFromTransformedPattern(pat);
		}
		
		return sum;
	}
	
	public static long getValueFromTransformedPattern(Pattern pattern) {
		Pattern transformedPattern;
		long colValue, rowValue;
		
		for (int i = 0; i < pattern.getPatternContent().size(); i++) {
			for (int j = 0; j < pattern.getPatternContent().get(i).length(); j++) {
				transformedPattern = changePatternAtIndex(pattern, j, i);
				
				colValue = determinePositionOfVerticalMirror(transformedPattern);
				rowValue = determinePositionOfHorizontalMirror(transformedPattern);
				
				if (colValue >= 0) {
					return colValue + 1;
				}
				
				if (rowValue >= 0) {
					return (rowValue + 1) * 100;
				}
			}
		}
		
		return -1;
	}
	
	public static Pattern changePatternAtIndex(Pattern pattern, int colIndex, int rowIndex) {
		List<String> changedPatternContent = new ArrayList<>(pattern.getPatternContent());
		
		char newSymbol = changedPatternContent.get(rowIndex).charAt(colIndex) == '#' ? '.' : '#';
		String newString = changedPatternContent.get(rowIndex).substring(0, colIndex) + newSymbol
				+ changedPatternContent.get(rowIndex).substring(colIndex + 1);
		
		changedPatternContent.set(rowIndex, newString);
		
		return new Pattern(changedPatternContent, pattern.getColumnIndex(), pattern.getRowIndex());
	}
	
	public static int determinePositionOfVerticalMirror(Pattern pattern) {
		String columnOne, columnTwo;
		int upperBound = pattern.getPatternContent().get(0).length() - 1;
		
		for (int colIndex = 0; colIndex < upperBound; colIndex++) {
			columnOne = getColumnFromPattern(pattern, colIndex);
			columnTwo = getColumnFromPattern(pattern, colIndex + 1);
			
			if (columnOne.equals(columnTwo)) {
				if (neighboringColumnsMatch(pattern, colIndex, upperBound) && colIndex != pattern.getColumnIndex()) {
					return colIndex;
				}
			}
		}
		
		return -1;
	}
	
	public static int determinePositionOfHorizontalMirror(Pattern pattern) {
		String rowOne, rowTwo;
		int upperBound = pattern.getPatternContent().size() - 1;
		
		for (int rowIndex = 0; rowIndex < upperBound; rowIndex++) {
			rowOne = pattern.getPatternContent().get(rowIndex);
			rowTwo = pattern.getPatternContent().get(rowIndex + 1);
			
			if (rowOne.equals(rowTwo)) {
				if (neighboringRowsMatch(pattern, rowIndex, upperBound) && rowIndex != pattern.getRowIndex()) {
					return rowIndex;
				}
			}
		}
		
		return -1;
	}
	
	public static boolean neighboringColumnsMatch(Pattern pattern, int index, int upperBound) {
		boolean isMatching = false;
		
		if (index == 0) {
			return true;
		} else if (index == upperBound - 1) {
			return true;
		}
		
		for (int i = 1; i < upperBound + 1; i++) {
			if (index - i >= 0 && index + 1 + i < upperBound + 1) {
				isMatching = getColumnFromPattern(pattern, index - i)
						.equals(getColumnFromPattern(pattern, index + 1 + i));
				if (!isMatching) {
					return false;
				}
			}
		}

		return isMatching;
	}
	
	public static boolean neighboringRowsMatch(Pattern pattern, int index, int upperBound) {
		boolean isMatching = false;
		
		if (index == 0) {
			return true;
		} else if (index == upperBound - 1) {
			return true;
		}
		
		for (int i = 1; i < upperBound + 1; i++) {
			if (index - i >= 0 && index + 1 + i < upperBound + 1) {
				isMatching = pattern.getPatternContent().get(index - i)
						.equals(pattern.getPatternContent().get(index + 1 + i));
				if (!isMatching) {
					return false;
				}
			}
		}
		
		return isMatching;
	}
	
	public static String getColumnFromPattern(Pattern pattern, int colIndex) {
		StringBuilder sb = new StringBuilder();
		List<Character> columnAsList = pattern.getPatternContent()
				.stream().map(p -> p.charAt(colIndex)).collect(Collectors.toList());
		
		for (char c : columnAsList) {
			sb.append(c);
		}
		
		return sb.toString();
	}

}
