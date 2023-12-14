package de.personal.adventofcode2023.day14.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RockFormationUtils {
	
	private RockFormationUtils() {
		
	}
	
	public static List<String> importRockFormation(String inputPath) {
		List<String> rockFormation = new ArrayList<>();
		String line;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			while ((line = br.readLine()) != null) {
				rockFormation.add(line);
			}
			
		} catch (IOException e) {
			System.err.println("Error during import.");
		}
		
		return rockFormation;
	}
	
	public static List<String> tiltRockFormationToNorth(List<String> rockFormation) {
		for (int colIndex = 0; colIndex < rockFormation.get(0).length(); colIndex++) {
			rockFormation = replaceOriginalColumnWithTiltedColumn(rockFormation,
					tiltColumnToNorth(getColumnFromRockFormation(rockFormation, colIndex)), colIndex);
		}
		
		return rockFormation;
	}
	
	public static List<String> tiltRockFormationToSouth(List<String> rockFormation) {
		for (int colIndex = 0; colIndex < rockFormation.get(0).length(); colIndex++) {
			rockFormation = replaceOriginalColumnWithTiltedColumn(rockFormation,
					tiltColumnToSouth(getColumnFromRockFormation(rockFormation, colIndex)), colIndex);
		}
		
		return rockFormation;
	}
	
	public static List<String> tiltRockFormationToEast(List<String> rockFormation) {
		for (int rowIndex = 0; rowIndex < rockFormation.size(); rowIndex++) {
			rockFormation = replaceOriginalRowWithTiltedRow(rockFormation,
					tiltRowToEast(convertStringToCharList(rockFormation.get(rowIndex))),
					rowIndex);
		}
		
		return rockFormation;
	}
	
	public static List<String> tiltRockFormationToWest(List<String> rockFormation) {
		for (int rowIndex = 0; rowIndex < rockFormation.size(); rowIndex++) {
			rockFormation = replaceOriginalRowWithTiltedRow(rockFormation,
					tiltRowToWest(convertStringToCharList(rockFormation.get(rowIndex))),
					rowIndex);
		}
		
		return rockFormation;
	}
	
	public static long calculateSum(List<String> rockFormation) {
		long sum = 0L;
		
//		for (int rowIndex = 0; rowIndex < rockFormation.size(); rowIndex++) {
//			sum += rockFormation.get(rowIndex).chars().filter(s -> s == 'O').count()
//					* (rockFormation.size() - rowIndex);
//			System.out.println(sum);
//		}
		
		for (int colIndex = 0; colIndex < rockFormation.get(0).length(); colIndex++) {
			for (int rowIndex = 0; rowIndex < rockFormation.size(); rowIndex++) {
				if (rockFormation.get(rowIndex).charAt(colIndex) == 'O') {
					sum += (rockFormation.size() - rowIndex);
				}
			}
			System.out.println(sum);
		}
		
		return sum;
	}
	
	public static long calculateSumAfterNCycles(List<String> rockFormation, int numOfCycles) {
		Map<String, Integer> cache = new HashMap<>();
		String rockFormationString;
		
		for (int i = 0; i < numOfCycles; i++) {
			rockFormation = cycleRockFormation(rockFormation);
			
			rockFormationString = rockFormationToString(rockFormation);
			
			if (cache.containsKey(rockFormationString)) {
				i += cache.get(rockFormationString);
			}

			cache.put(rockFormationString, i);
		}
		
		rockFormation.forEach(System.out::println);
		
		return calculateSum(rockFormation);
	}
	
	private static String rockFormationToString(List<String> rockFormation) {
		StringBuilder sb = new StringBuilder();
		for (String line : rockFormation) {
			sb.append(line);
		}
		return sb.toString();
	}
	
	public static List<String> cycleRockFormation(List<String> rockFormation) {
		rockFormation = tiltRockFormationToNorth(rockFormation);
		rockFormation = tiltRockFormationToWest(rockFormation);
		rockFormation = tiltRockFormationToSouth(rockFormation);
		rockFormation = tiltRockFormationToEast(rockFormation);
		
		return rockFormation;
	}
	
	public static List<Character> convertStringToCharList(String str) {
		return str.chars().mapToObj(n -> (char)n).collect(Collectors.toList());
	}
	
	public static List<Character> getColumnFromRockFormation(List<String> rockFormation, int colIndex) {
		return rockFormation.stream().map(r -> r.charAt(colIndex)).collect(Collectors.toList());
	}
	
	public static int countRoundRocksBeetweenCubeRocks(List<Character> column, int start, int end) {
		return column.subList(start, end)
				.stream().filter(c -> c == 'O').collect(Collectors.toList()).size();
	}
	
	public static List<Character> replaceRoundRocks(List<Character> column, int start, int end,
			boolean northOrWest) {
		int rockCount = countRoundRocksBeetweenCubeRocks(column, start, end);
		
		if (northOrWest) {
			for (int i = start; i < start + rockCount; i++) {
				column.set(i, 'O');
			}
			
			for (int j = start + rockCount; j < end; j++) {
				column.set(j, '.');
			}
		} else {
			for (int i = end - rockCount; i < end; i++) {
				column.set(i, 'O');
			}
			
			for (int j = start; j < end - rockCount; j++) {
				column.set(j, '.');
			}
		}
		
		return column;
	}
	
	public static List<Character> tiltColumnToNorth(List<Character> column) {
		int indexFirstHashtag, indexSecondHashtag;
		
		for (int i = 0; i < column.size(); i++) {
			if (column.get(i) == '#' || i == 0) {
				indexFirstHashtag = i + 1;
				
				if (i == 0 && column.get(i) != '#') {
					indexFirstHashtag = 0;
				} else if (i == column.size() - 2 && column.get(i) == '#') {
					return column;
				}
				
				for (int j = indexFirstHashtag; j < column.size(); j++) {
					if (column.get(j) == '#' || j == column.size() - 1) {
						indexSecondHashtag = j == column.size() - 1 ? column.size() : j;
						
						if (column.get(j) == '#') {
							indexSecondHashtag = j;
						}
						
						column = replaceRoundRocks(column, indexFirstHashtag, indexSecondHashtag, true);
						break;
					}
				}
			}
		}
		
		return column;
	}
	
	public static List<Character> tiltColumnToSouth(List<Character> column) {
		int indexFirstHashtag, indexSecondHashtag;
		
		for (int i = 0; i < column.size(); i++) {
			if (column.get(i) == '#' || i == 0) {
				indexFirstHashtag = i + 1;
				
				if (i == 0 && column.get(i) != '#') {
					indexFirstHashtag = 0;
				} else if (i == column.size() - 2 && column.get(i) == '#') {
					return column;
				}
				
				for (int j = indexFirstHashtag; j < column.size(); j++) {
					if (column.get(j) == '#' || j == column.size() - 1) {
						indexSecondHashtag = j == column.size() - 1 ? column.size() : j;
						
						if (column.get(j) == '#') {
							indexSecondHashtag = j;
						}
						
						column = replaceRoundRocks(column, indexFirstHashtag, indexSecondHashtag, false);
						break;
					}
				}
			}
		}
		
		return column;
	}
	
	public static List<Character> tiltRowToEast(List<Character> row) {
		int indexFirstHashtag, indexSecondHashtag;
		
		for (int i = 0; i < row.size(); i++) {
			if (row.get(i) == '#' || i == 0) {
				indexFirstHashtag = i + 1;
				
				if (i == 0 && row.get(i) != '#') {
					indexFirstHashtag = 0;
				} else if (i == row.size() - 2 && row.get(i) == '#') {
					return row;
				}
				
				for (int j = indexFirstHashtag; j < row.size(); j++) {
					if (row.get(j) == '#' || j == row.size() - 1) {
						indexSecondHashtag = j == row.size() - 1 ? row.size() : j;
						
						if (row.get(j) == '#') {
							indexSecondHashtag = j;
						}
						
						row = replaceRoundRocks(row, indexFirstHashtag, indexSecondHashtag, false);
						break;
					}
				}
			}
		}
		
		return row;
	}
	
	public static List<Character> tiltRowToWest(List<Character> row) {
		int indexFirstHashtag, indexSecondHashtag;
		
		for (int i = 0; i < row.size(); i++) {
			if (row.get(i) == '#' || i == 0) {
				indexFirstHashtag = i + 1;
				
				if (i == 0 && row.get(i) != '#') {
					indexFirstHashtag = 0;
				} else if (indexFirstHashtag == row.size()) {
					indexFirstHashtag = i;
				} else if (i == row.size() - 2 && row.get(i) == '#') {
					return row;
				}
				
				for (int j = indexFirstHashtag; j < row.size(); j++) {
					if (row.get(j) == '#' || j == row.size() - 1) {
						indexSecondHashtag = j == row.size() - 1 ? row.size() : j;
						
						if (row.get(j) == '#') {
							indexSecondHashtag = j;
						}
						
						row = replaceRoundRocks(row, indexFirstHashtag, indexSecondHashtag, true);
						break;
					}
				}
			}
		}

		return row;
	}
	
	public static List<String> replaceOriginalColumnWithTiltedColumn(List<String> rockFormation,
			List<Character> tiltedColumn, int colIndex) {
		StringBuilder sb = new StringBuilder();
		int tiltedColumnCharIndex = 0;
		for (int rowIndex = 0; rowIndex < rockFormation.size(); rowIndex++) {
			sb.append(rockFormation.get(rowIndex).substring(0, colIndex));
			sb.append(tiltedColumn.get(tiltedColumnCharIndex));
			sb.append(rockFormation.get(rowIndex).substring(colIndex + 1));
			rockFormation.set(rowIndex, sb.toString());
			tiltedColumnCharIndex++;
			sb.setLength(0);
		}
		
		return rockFormation;
	}
	
	public static List<String> replaceOriginalRowWithTiltedRow(List<String> rockFormation,
			List<Character> tiltedRow, int rowIndex) {
		StringBuilder sb = new StringBuilder();

		for (char c : tiltedRow) {
			sb.append(c);
		}
		
		rockFormation.set(rowIndex, sb.toString());
		return rockFormation;
	}
}
