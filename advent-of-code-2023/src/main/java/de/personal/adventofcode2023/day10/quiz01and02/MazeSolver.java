package de.personal.adventofcode2023.day10.quiz01and02;

import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MazeSolver {
	
	private MazeSolver() {}
	
	public static int calculateSumOfTiles(List<String> allLines, List<List<Integer>> loop) {
		Polygon polygon = transformLoopToPolygon(loop);
		List<Integer> vertice = new ArrayList<>();
		int sum = 0;
		
		for (int lineIndex = 0; lineIndex < allLines.size(); lineIndex++) {
			for (int i = 0; i < allLines.get(lineIndex).length(); i++) {
				vertice = new ArrayList<>(Arrays.asList(lineIndex, i));
				
				if (isTileInLoop(i, lineIndex, polygon) && !loop.contains(vertice)) {
					sum++;
				}
			}
		}			
		
		return sum;
	}

	public static int determineMaximumSteps(List<List<Integer>> loop) {
		if ((loop.size() - 1) % 2 == 0) {
			return (loop.size() - 1) / 2;
		} else {
			return (loop.size()) / 2 + 1;
		}
	}
	
	public static Polygon transformLoopToPolygon(List<List<Integer>> loop) {
        Polygon polygon = new Polygon();

        for(List<Integer> point : loop) {
            polygon.addPoint(point.get(1), point.get(0));
        }
        
        return polygon;
	}
	
	public static boolean isTileInLoop(int x, int y, Polygon polygon) {
        return polygon.contains(new Point(x, y));
    }
	
	public static List<List<Integer>> findTheLoop(List<String> allLines) {
		List<Integer> startingPosition = findStartingPosition(allLines);
		List<List<Integer>> nextPipePositions = new ArrayList<>(Arrays.asList(startingPosition));
		
		allLines.set(startingPosition.get(0), allLines.get(startingPosition.get(0)).replace('S',
				determineShapeOfS(allLines, startingPosition)));

		do {
			if (nextPipePositions.size() == 1) {
				nextPipePositions.add(determineNextPipe(allLines, nextPipePositions.get(nextPipePositions.size() - 1),
						new ArrayList<>()));
			} else {
				nextPipePositions.add(determineNextPipe(allLines, nextPipePositions.get(nextPipePositions.size() - 1),
						nextPipePositions.get(nextPipePositions.size() - 2)));
			}
		} while (!startingPosition.equals(nextPipePositions.get(nextPipePositions.size() - 1)));
		
		return nextPipePositions;
	}
	
	public static List<Integer> determineNextPipe(List<String> allLines, List<Integer> currentPosition,
			List<Integer> previousPosition) {
		List<Integer> nextPosition = new ArrayList<>();
		
		char currentSymbol = allLines.get(currentPosition.get(0)).charAt(currentPosition.get(1)),
				north, south, east, west;
		
		if (currentPosition.get(0) > 0) {
			north = allLines.get(currentPosition.get(0) - 1).charAt(currentPosition.get(1));
		} else {
			north = 'x';
		}
		
		if (currentPosition.get(0) < allLines.size() - 1) {
			south = allLines.get(currentPosition.get(0) + 1).charAt(currentPosition.get(1));
		} else {
			south = 'x';
		}
		
		if (currentPosition.get(1) > 0) {
			west = allLines.get(currentPosition.get(0)).charAt(currentPosition.get(1) - 1);
		} else {
			west = 'x';
		}

		if (currentPosition.get(1) < allLines.get(currentPosition.get(0)).length() - 1) {
			east = allLines.get(currentPosition.get(0)).charAt(currentPosition.get(1) + 1);
		} else {
			east = 'x';
		}
		
		if ((currentSymbol == '|' || currentSymbol == 'L' || currentSymbol == 'J')
				&& (north == '|' || north == '7' || north == 'F')) {
			nextPosition.add(currentPosition.get(0) - 1); 
			nextPosition.add(currentPosition.get(1)); 
			
			if (!listsAreEqual(previousPosition, nextPosition)) {
				return nextPosition;
			} else {
				nextPosition.clear();
			}
		}
			
		if ((currentSymbol == '|' || currentSymbol == '7' || currentSymbol == 'F')
				&& (south == '|' || south == 'L' || south == 'J')) {
			nextPosition.add(currentPosition.get(0) + 1);
			nextPosition.add(currentPosition.get(1));
			
			if (!listsAreEqual(previousPosition, nextPosition)) {
				return nextPosition;
			} else {
				nextPosition.clear();
			}
		}
			
		if ((currentSymbol == '-' || currentSymbol == 'F' || currentSymbol == 'L') &&
				(east == '-' || east == '7' || east == 'J')) {
			nextPosition.add(currentPosition.get(0));
			nextPosition.add(currentPosition.get(1) + 1);
			
			if (!listsAreEqual(previousPosition, nextPosition)) {
				return nextPosition;
			} else {
				nextPosition.clear();
			}
		}
			
		if ((currentSymbol == '-' || currentSymbol == '7' || currentSymbol == 'J') &&
				(west == '-' || west == 'F' || west == 'L')) {
			nextPosition.add(currentPosition.get(0));
			nextPosition.add(currentPosition.get(1) - 1);
			
			if (!listsAreEqual(previousPosition, nextPosition)) {
				return nextPosition;
			} else {
				nextPosition.clear();
			}
		}
	
		return nextPosition;
	}
	
	public static char determineShapeOfS(List<String> allLines, List<Integer> currentPosition) {
		char currentSymbol = allLines.get(currentPosition.get(0)).charAt(currentPosition.get(1)),
				north, south, east, west;
		
		if (currentPosition.get(0) > 0) {
			north = allLines.get(currentPosition.get(0) - 1).charAt(currentPosition.get(1));
		} else {
			north = 'x';
		}
		
		if (currentPosition.get(0) < allLines.size() - 1) {
			south = allLines.get(currentPosition.get(0) + 1).charAt(currentPosition.get(1));
		} else {
			south = 'x';
		}
		
		if (currentPosition.get(1) > 0) {
			west = allLines.get(currentPosition.get(0)).charAt(currentPosition.get(1) - 1);
		} else {
			west = 'x';
		}

		if (currentPosition.get(1) < allLines.get(currentPosition.get(0)).length() - 1) {
			east = allLines.get(currentPosition.get(0)).charAt(currentPosition.get(1) + 1);
		} else {
			east = 'x';
		}
		
		if ('S' == currentSymbol) {
			if (north == '|' && (east == 'J' || east == '7' || east == '-')) {
				return 'L';
			} else if (north == '|' && (west == 'F' || west == 'L' || west == '-')) {
				return 'J';
			} else if (north == '|' && south == '|') {
				return '|';
			} else if ((south == '|' || south == 'J' || south == 'L')
					&& (east == 'J' || east == '7' || east == '-')) {
				return 'F';
			} else if ((south == '|' || south == 'J' || south == 'L')
					&& (west == 'F' || west == 'L' || west == '-')) {
				return '7';
			}
		}

		return 'S';
	}
	
	public static List<Integer> findStartingPosition(List<String> allLines) {
		for (String line : allLines) {
			for (int i = 0; i < line.length(); i++) {
				if ('S' == line.charAt(i)) {
					return new ArrayList<>(Arrays.asList(allLines.indexOf(line), i));
				}
			}
		}
		
		return new ArrayList<>(Arrays.asList(-1, -1));
	}
	
	public static List<String> importAllLines(String inputPath) {
		List<String> allLines = new ArrayList<>();
		String line;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			while ((line = br.readLine()) != null) {
				allLines.add(line);
			}
			
		} catch (IOException e) {
			System.err.println("Error during data import.");
		}
		
		return allLines;
	}
	
	private static boolean listsAreEqual(List<Integer> list1, List<Integer> list2) {
		return list1.equals(list2);
	}
}