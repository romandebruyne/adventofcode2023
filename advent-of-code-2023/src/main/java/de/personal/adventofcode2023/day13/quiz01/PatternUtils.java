package de.personal.adventofcode2023.day13.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatternUtils {
	
	private PatternUtils() {}
	
	public static List<Pattern> importAllPatterns(String inputPath) {
		List<String> patternContent = new ArrayList<>();
		List<Pattern> allPatterns = new ArrayList<>();
		String line;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			for (int i = 0; i < 1408; i++) {
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
		int colIndex, rowIndex;
		int sum = 0;
		
		for (Pattern pat : allPatterns) {
			colIndex = getIndexOfVerticallyMirroredColumn(pat);
			rowIndex = getIndexOfHorizontallyMirroredRow(pat);
			
			if (colIndex >= 0) {
				sum += (colIndex + 1);
			}
			
			if (rowIndex >= 0) {
				sum += (rowIndex + 1) * 100;
			}
		}
		
		return sum;
	}
	
	public static boolean outlyingColumnsMatch(Pattern pattern, int index, int upperBound) {
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
	
	public static boolean outlyingRowsMatch(Pattern pattern, int index, int upperBound) {
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
	
	
	public static int getIndexOfVerticallyMirroredColumn(Pattern pattern) {
		String columnOne, columnTwo;
		int upperBound = pattern.getPatternContent().get(0).length() - 1;
		
		for (int colIndex = 0; colIndex < upperBound; colIndex++) {
			columnOne = getColumnFromPattern(pattern, colIndex);
			columnTwo = getColumnFromPattern(pattern, colIndex + 1);
			
			if (columnOne.equals(columnTwo)) {
				if (outlyingColumnsMatch(pattern, colIndex, upperBound)) {
					return colIndex;
				}
			}
		}
		
		return -1;
	}
	
	public static int getIndexOfHorizontallyMirroredRow(Pattern pattern) {
		String rowOne, rowTwo;
		int upperBound = pattern.getPatternContent().size() - 1;
		
		for (int rowIndex = 0; rowIndex < upperBound; rowIndex++) {
			rowOne = pattern.getPatternContent().get(rowIndex);
			rowTwo = pattern.getPatternContent().get(rowIndex + 1);
			
			if (rowOne.equals(rowTwo)) {
				if (outlyingRowsMatch(pattern, rowIndex, upperBound)) {
					return rowIndex;
				}
			}
		}
		
		return -1;
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
