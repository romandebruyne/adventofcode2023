package de.personal.adventofcode2023.day14.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
	
	public static List<String> transformRockFormation(List<String> rockFormation) {
		for (int colIndex = 0; colIndex < rockFormation.size(); colIndex++) {
			rockFormation = replaceOriginalColumnWithTiltedColumn(rockFormation,
					tiltColumnToNorth(getColumnFromRockFormation(rockFormation, colIndex)), colIndex);
		}
		
		return rockFormation;
	}
	
	public static List<Character> getColumnFromRockFormation(List<String> rockFormation, int colIndex) {
		return rockFormation.stream().map(r -> r.charAt(colIndex)).collect(Collectors.toList());
	}
	
	private static int countRoundRocksBeetweenCubeRocks(List<Character> column, int start, int end) {
		return column.subList(start, end)
				.stream().filter(c -> c == 'O').collect(Collectors.toList()).size();
	}
	
	public static long calculateSum(List<String> rockFormation) {
		List<String> transformedRockFormation = transformRockFormation(rockFormation);
		long sum = 0L;
		
		for (int rowIndex = 0; rowIndex < transformedRockFormation.size(); rowIndex++) {
			sum += transformedRockFormation.get(rowIndex).chars().filter(s -> s == 'O').count()
					* (transformedRockFormation.size() - rowIndex);
		}
		
		return sum;
	}
	

	public static List<Character> replaceRoundRocks(List<Character> column, int start, int end) {
		int rockCount = countRoundRocksBeetweenCubeRocks(column, start, end);
		
		for (int i = start; i < start + rockCount; i++) {
			column.set(i, 'O');
		}
		
		for (int j = start + rockCount; j < end; j++) {
			column.set(j, '.');
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
						column = replaceRoundRocks(column, indexFirstHashtag, indexSecondHashtag);
						break;
					}
				}
			}
		}
		
		return column;
	}
	
	public static List<String> replaceOriginalColumnWithTiltedColumn(List<String> rockFormation,
			List<Character> tiltedColumn, int colIndex) {
		
		int tiltedColumnCharIndex = 0;
		for (int rowIndex = 0; rowIndex < rockFormation.size(); rowIndex++) {
			rockFormation.set(rowIndex, rockFormation.get(rowIndex).substring(0, colIndex)
					+ tiltedColumn.get(tiltedColumnCharIndex) + rockFormation.get(rowIndex).substring(colIndex + 1));
			tiltedColumnCharIndex++;
		}
		
		return rockFormation;
		
	}
}
