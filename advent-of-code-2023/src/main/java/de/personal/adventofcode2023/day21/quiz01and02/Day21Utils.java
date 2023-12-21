package de.personal.adventofcode2023.day21.quiz01and02;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day21Utils {
	
	private Day21Utils() {}
	
	public static char[][] importFarmStructure(String inputPath, int size) {
		char[][] structure = new char[size][size];
		int rowIndex = 0;
		String line;

		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			while ((line = br.readLine()) != null) {
				for (int colIndex = 0; colIndex < line.length(); colIndex++) {
					structure[rowIndex][colIndex] = line.charAt(colIndex);
				}
				rowIndex++;
			}
		} catch (IOException e) {
			System.err.println("Data import error.");
		}

		return structure;
	}
	
	public static boolean positionIsInList(int[] position, List<int[]> list) {
		for (int[] listItem : list) {
			if (listItem[0] == position[0] && listItem[1] == position[1]) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void drawPositions(char[][] farm, List<int[]> positions) {
		int[] position;
		
		for (int row = 0; row < farm.length; row++) {
			for (int col = 0; col < farm[0].length; col++) {
				position = new int[2];
				position[0] = row;
				position[1] = col;
				
				if (positionIsInList(position, positions)) {
					System.out.print('0');
				} else {
					System.out.print(farm[row][col]);
				}
			}
			System.out.println();
		}
	}
}
