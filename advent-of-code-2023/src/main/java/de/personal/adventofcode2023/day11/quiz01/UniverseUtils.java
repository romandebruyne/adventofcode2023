package de.personal.adventofcode2023.day11.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniverseUtils {
	
	private UniverseUtils() {}
	
	public static List<String> importUniverse(String inputPath) {
		String line;
		List<String> universe = new ArrayList<>();
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			while ((line = br.readLine()) != null) {
				universe.add(line);
			}
		} catch (IOException e) {
			System.err.println("Error during data import.");
		}
		
		return universe;
	}
	
	public static List<String> expandUniverse(List<String> universe) {
		List<String> expandedUniverse = universe;
		List<Integer> columnIndexes = getColumnsIndexesWithNoGalaxy(expandedUniverse);
		Collections.reverse(columnIndexes);
		String expandedPart;
		
		// Column wise expansion
		for (int columnIndex : columnIndexes) {
			for (int j = 0; j < expandedUniverse.size(); j++) {
				expandedPart = expandedUniverse.get(j).substring(0, columnIndex + 1) + "."
						+ expandedUniverse.get(j).substring(columnIndex + 1);
				expandedUniverse.set(j, expandedPart);
			}
		}
		
		// Row wise expansion
		for (int i = 0; i < expandedUniverse.size(); i++) {
			if (!expandedUniverse.get(i).contains("#")) {
				expandedUniverse.add(i, expandedUniverse.get(i));
				i++;
			}
		}
			
		return expandedUniverse;
	}
	
	public static int calculateSumOfDistances(List<String> universe) {
		List<String> expandedUniverseWithNumbers = UniverseUtils.expandUniverse(universe);
		Map<Integer, List<Integer>> coords = UniverseUtils.findCoordinatesOfGalaxy(expandedUniverseWithNumbers);
		Map<String, Integer> dists = UniverseUtils.determineDistancesBeetweenCoordinates(coords);
		
		return dists.values().stream().reduce(0, (a, b) -> a + b);
	}
	
	public static Map<String, Integer> determineDistancesBeetweenCoordinates(Map<Integer, 
			List<Integer>> coordinates) {
		
		Map<String, Integer> distances = new HashMap<>();
		List<Integer> firstCoordinate, secondCoordinate;
		
		for (int i = 0; i < coordinates.size(); i++) {
			firstCoordinate = coordinates.get(i + 1);
			
			for (int j = 0; j < coordinates.size(); j++) {
				if ((i + 1) != (j + 1)) {
					secondCoordinate = coordinates.get(j + 1);
					if (distances.containsKey((i + 1) + ", " + (j + 1)) || 
							distances.containsKey((j + 1) + ", " + (i + 1))) {
						continue;
					} else {
						distances.put((i + 1) + ", " + (j + 1), 
								calculateManhattanDistance(firstCoordinate, secondCoordinate));
					}
				}
			}
		}
		
		return distances;
	}
	
	public static int calculateManhattanDistance(List<Integer> pointOne, List<Integer> pointTwo) {
		return Math.abs(pointOne.get(0) - pointTwo.get(0)) + Math.abs(pointOne.get(1) - pointTwo.get(1));
	}
	
	public static Map<Integer, List<Integer>> findCoordinatesOfGalaxy(List<String> expandedUniverse) {
		Map<Integer, List<Integer>> coordinates = new HashMap<>();
		int galaxyCount = 1;
		
		for (int i = 0; i < expandedUniverse.size(); i++) {
			for (int j = 0; j < expandedUniverse.get(i).length(); j++) {
				if (expandedUniverse.get(i).charAt(j) == '#') {
					coordinates.put(galaxyCount, new ArrayList<>(Arrays.asList(j, i)));
					galaxyCount++;
				}
			}
		}
		
		return coordinates;
	}
	
	public static List<Integer> getColumnsIndexesWithNoGalaxy(List<String> universe) {
		List<Integer> columnIndexesWithNoGalaxy = new ArrayList<>();
		for (int i = 0; i < universe.size(); i++) {
			if (countGalaxiesInColumn(universe, i) == 0) {
				columnIndexesWithNoGalaxy.add(i);
			}
		}
		return columnIndexesWithNoGalaxy;
	}
	
	public static long countGalaxiesInColumn(List<String> universe, int columnIndex) {
		return universe.stream().filter(g -> g.charAt(columnIndex) == '#').count();
	}
}
