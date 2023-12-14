package de.personal.adventofcode2023.day14.quiz01and02;

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
		
		for (int rowIndex = 0; rowIndex < rockFormation.size(); rowIndex++) {
			sum += rockFormation.get(rowIndex).chars().filter(s -> s == 'O').count()
					* (rockFormation.size() - rowIndex);
		}
		
		return sum;
	}
	
	public static long calculateSumAfterNCycles(List<String> rockFormation, int numOfCycles) {
		Map<String, Integer> cache = new HashMap<>();
		String rockFormationString;
		long delta;
		
		for (int i = 0; i < numOfCycles; i++) {
			rockFormation = cycleRockFormation(rockFormation);
			
			rockFormationString = rockFormationToString(rockFormation);
			
			if (cache.containsKey(rockFormationString)) {
				delta = i - cache.get(rockFormationString);
				i += delta * ((numOfCycles - i) / delta);
			}

			cache.put(rockFormationString, i);
		}
		
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
	
	private static int findHashtagIndex(List<Character> column, int start) {
		for (int i = start; i < column.size(); i++) {
			if (column.get(i) == '#') {
				return i;
			}
		}
		
		return column.size();
	}
	
	public static List<Character> tiltColumnToNorth(List<Character> column) {
		int end;
		
		for (int i = 0; i < column.size(); i++) {
			end = findHashtagIndex(column, i);
			column = replaceRoundRocks(column, i, end, true);
			i = end;
		}
		
		return column;
	}
	
	public static List<Character> tiltColumnToSouth(List<Character> column) {
		int end;
		
		for (int i = 0; i < column.size(); i++) {
			end = findHashtagIndex(column, i);
			column = replaceRoundRocks(column, i, end, false);
			i = end;
		}
		
		return column;
	}
	
	public static List<Character> tiltRowToEast(List<Character> row) {
		int end;
		
		for (int i = 0; i < row.size(); i++) {
			end = findHashtagIndex(row, i);
			row = replaceRoundRocks(row, i, end, false);
			i = end;
		}
		
		return row;
	}
	
	public static List<Character> tiltRowToWest(List<Character> row) {
		int end;
		
		for (int i = 0; i < row.size(); i++) {
			end = findHashtagIndex(row, i);
			row = replaceRoundRocks(row, i, end, true);
			i = end;
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
