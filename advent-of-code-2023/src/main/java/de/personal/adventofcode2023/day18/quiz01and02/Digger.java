package de.personal.adventofcode2023.day18.quiz01and02;

import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Digger {
	
	private Digger() {}
	
	public static List<DigInstruction> importDigPlan(String inputPath, boolean partOne) {
		List<DigInstruction> digPlan = new ArrayList<>();
		String line, hexCodeMoves;
		char directionAsNum;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			while ((line = br.readLine()) != null) {
				if (partOne) {
					digPlan.add(new DigInstruction(Integer.parseInt(line.split(" ")[1]),
							line.split(" ")[0].charAt(0), line.split(" ")[2]));
				} else {
					hexCodeMoves = line.split(" ")[2].substring(line.split(" ")[2].indexOf('#') + 1,
							line.split(" ")[2].indexOf('#') + 6);
					directionAsNum = line.split(" ")[2].charAt(line.split(" ")[2].indexOf('#') + 6);
					digPlan.add(new DigInstruction(
							Integer.parseInt(hexCodeMoves, 16),
							translateNumToDirection(directionAsNum)));
				}
			}
		} catch (IOException e) {
			System.err.println("Error during data import.");
		}
		
		return digPlan;
	}
	
	public static char translateNumToDirection(char numAsChar) {
		switch (numAsChar) {
		case '0':
			return 'R';
		case '1':
			return 'D';
		case '2':
			return 'L';
		default:
			return 'U';
		}
	}
	
	public static List<Point> getEdges(List<DigInstruction> digPlan) {
		Point startingEdge = new Point(0, 0);
		List<Point> edges = new ArrayList<>();
		
		edges.add(startingEdge);

		for (DigInstruction instruction : digPlan) {
			edges.addAll(createNewEdges(edges.get(edges.size() - 1), instruction, instruction.getDirection()));
		}
		
		return edges.stream().distinct().collect(Collectors.toList());
	}
	
	public static List<Point> createNewEdges(Point currentEdge, DigInstruction instruction, char direction) {
		switch (direction) {
		case 'U':
			return IntStream.rangeClosed(1, instruction.getNumberOfMoves()).parallel()
					.mapToObj(i -> new Point(currentEdge.x, currentEdge.y - i)).collect(Collectors.toList());
		case 'D':
			return IntStream.rangeClosed(1, instruction.getNumberOfMoves()).parallel()
					.mapToObj(i -> new Point(currentEdge.x, currentEdge.y + i)).collect(Collectors.toList());
		case 'R':
			return IntStream.rangeClosed(1, instruction.getNumberOfMoves()).parallel()
					.mapToObj(i -> new Point(currentEdge.x + i, currentEdge.y)).collect(Collectors.toList());
		default:
			return IntStream.rangeClosed(1, instruction.getNumberOfMoves()).parallel()
					.mapToObj(i -> new Point(currentEdge.x - i, currentEdge.y)).collect(Collectors.toList());
		}
	}
	
	public static List<Point> reshapeEdges(List<Point> edges) {
		int minX = edges.stream().map(e -> e.x).reduce(Integer.MAX_VALUE, (a, b) -> Integer.min(a, b)),
				minY = edges.stream().map(e -> e.y).reduce(Integer.MAX_VALUE, (a, b) -> Integer.min(a, b));
		
		return edges.stream().map(e -> new Point(e.x - minX, e.y - minY)).collect(Collectors.toList());
	}
	
	public static Polygon transformEdgesToPolygon(List<Point> edges) {
		Polygon polygon = new Polygon();
		
		for (Point edge : edges) {
			polygon.addPoint(edge.x, edge.y);
		}
		
		return polygon;
	}
	
	public static long getNumOfPointsInPolygon(List<Point> edges) {
		List<Point> reshapedEdges = reshapeEdges(edges);
		Polygon polygon = transformEdgesToPolygon(reshapedEdges);
		Set<Point> pointsInside = new HashSet<>(reshapeEdges(reshapedEdges));
		
		int maxX = reshapedEdges.stream().map(e -> e.x).reduce(0, (a, b) -> Integer.max(a, b)),
				maxY = reshapedEdges.stream().map(e -> e.y).reduce(0, (a, b) -> Integer.max(a, b));

		System.out.println(maxY);
		System.out.println(maxX);
		
		for (int y = 0; y <= maxY; y++) {
			for (int x = 0; x <= maxX; x++) {
				if (polygon.contains(new Point(x, y))) {
					pointsInside.add(new Point(x, y));
				}
			}
		}
		
		return pointsInside.size();
	}
	
	public static long getNumOfPointsInPolygonWithShoeLaceAlgorithm(List<Point> edges) {
		long sum = 0;
		
		sum += (edges.get(edges.size() - 1).x + edges.get(0).x) * (edges.get(edges.size() - 1).y - edges.get(0).y);
		for (int i = 1; i < edges.size(); i++) {
			sum += (edges.get(i - 1).x + edges.get(i).x) * (edges.get(i - 1).y - edges.get(i).y);
		}
		
		return (Math.abs(sum) / 2) + (edges.size() / 2) + 1;
	}
	
	public static void drawEdges(List<Point> edges) {
		int maxX = edges.stream().map(e -> e.x).reduce(0, (a, b) -> Integer.max(a, b)),
				maxY = edges.stream().map(e -> e.y).reduce(0, (a, b) -> Integer.max(a, b));
		
		for (int y = 0; y <= maxY; y++) {
			for (int x = 0; x <= maxX; x++) {
				if (edges.contains(new Point(x, y))) {
					System.out.print('#');
				} else {
					System.out.print('.');
					
				}
			}
			System.out.println();
		}
	}
}
