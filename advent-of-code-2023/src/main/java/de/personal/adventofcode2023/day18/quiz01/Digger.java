package de.personal.adventofcode2023.day18.quiz01;

import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Digger {
	
	private Digger() {}
	
	public static List<DigInstruction> importDigPlan(String inputPath) {
		List<DigInstruction> digPlan = new ArrayList<>();
		String line;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			while ((line = br.readLine()) != null) {
				digPlan.add(new DigInstruction(Integer.parseInt(line.split(" ")[1]),
						line.split(" ")[0].charAt(0), line.split(" ")[2]));
			}
		} catch (IOException e) {
			System.err.println("Error during data import.");
		}
		
		return digPlan;
	}
	
	public static List<Point> digEdges(List<DigInstruction> digPlan) {
		Point startingEdge = new Point(0, 0);
		Point currentEdge, newEdge;
		List<Point> edges = new ArrayList<>();
		
		edges.add(startingEdge);
		
		for (DigInstruction instruction : digPlan) {
			currentEdge = edges.get(edges.size() - 1);
			
			switch (instruction.getDirection()) {
			case 'U':
				for (int i = 1; i <= instruction.getNumberOfMoves(); i++) {
					newEdge = new Point(currentEdge.x, currentEdge.y - i);
					if (!edges.contains(newEdge)) {
						edges.add(newEdge);
					}
				}
				break;
			case 'D':
				for (int i = 1; i <= instruction.getNumberOfMoves(); i++) {
					newEdge = new Point(currentEdge.x, currentEdge.y + i);
					if (!edges.contains(newEdge)) {
						edges.add(newEdge);
					}
				}
				break;
			case 'R':
				for (int i = 1; i <= instruction.getNumberOfMoves(); i++) {
					newEdge = new Point(currentEdge.x + i, currentEdge.y);
					if (!edges.contains(newEdge)) {
						edges.add(newEdge);
					}
				}
				break;
			case 'L':
				for (int i = 1; i <= instruction.getNumberOfMoves(); i++) {
					newEdge = new Point(currentEdge.x - i, currentEdge.y);
					if (!edges.contains(newEdge)) {
						edges.add(newEdge);
					}
				}
				break;
			}
		}
		
		return edges;
	}
	
	public static List<Point> reshapeEdges(List<Point> edges) {
		int minX = edges.stream().map(e -> e.x).reduce(Integer.MAX_VALUE, (a, b) -> Integer.min(a, b)),
				minY = edges.stream().map(e -> e.y).reduce(Integer.MAX_VALUE, (a, b) -> Integer.min(a, b));
		List<Point> reshapedEdges = new ArrayList<>();
		
		for (Point edge : edges) {
			reshapedEdges.add(new Point(edge.x - minX, edge.y - minY));
		}
		
		return reshapedEdges;
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
		List<Point> pointsInside = new ArrayList<>(reshapeEdges(reshapedEdges));
		
		int maxX = reshapedEdges.stream().map(e -> e.x).reduce(0, (a, b) -> Integer.max(a, b)),
				maxY = reshapedEdges.stream().map(e -> e.y).reduce(0, (a, b) -> Integer.max(a, b));
		
		for (int y = 0; y <= maxY; y++) {
			for (int x = 0; x <= maxX; x++) {
				if (polygon.contains(new Point(x, y)) && !pointsInside.contains(new Point(x, y))) {
					pointsInside.add(new Point(x, y));
				}
			}
		}
		
		return pointsInside.size();
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
