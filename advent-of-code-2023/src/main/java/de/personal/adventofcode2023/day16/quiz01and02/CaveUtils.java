package de.personal.adventofcode2023.day16.quiz01and02;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CaveUtils {

	private CaveUtils() {
	}

	public static char[][] importCaveStructure(String inputPath, int size) {
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

	public static boolean containsBeam(List<Beam> beams, Beam beamToCompare) {
		return beams.stream().anyMatch(b -> b.equals(beamToCompare));
	}

	public static boolean beamIsInBound(char[][] caveStructure, Beam beam) {
		return beam.getRow() >= 0 && beam.getRow() < caveStructure.length && beam.getCol() >= 0
				&& beam.getCol() < caveStructure[0].length;
	}
	
	public static void drawCave(char[][] caveStructure, List<List<Integer>> tiles) {
		for (int row = 0; row < caveStructure.length; row++) {
			for (int col = 0; col < caveStructure[0].length; col++) {
				if (tiles.contains(Arrays.asList(row, col))) {
					System.out.print('#');
				} else {
					System.out.print(caveStructure[row][col]);
				}
			}
			System.out.println();
		}
	}
}