package de.personal.adventofcode2023.day08.quiz02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.personal.adventofcode2023.day08.quiz01.Navigator;
import de.personal.adventofcode2023.day08.quiz01.Node;

public class GhostNavigator extends Navigator {
	
	public List<String> getAllNodesStartingWithA(List<Node> allNodes) {
		List<String> nodesStartingWithA = new ArrayList<>();
		
		for (Node node : allNodes) {
			if ("A".equals(node.getLabel().substring(2))) {
				nodesStartingWithA.add(node.getLabel());
			}
		}
		
		return nodesStartingWithA;
	}
	
	public int determineStepsToDestinationWithBruteForce(String path, List<String> starts, Map<String, List<String>> nodeMap) {
		int steps = 0, pathIndex = 0;
		boolean destinationFound = false;
		List<String> nextNodes = starts;
		
		while (!destinationFound) {
			steps++;
			
			if (path.charAt(pathIndex) == 'L') {
				nextNodes = nextNodes
						.stream().map(n -> nodeMap.get(n).get(0)).collect(Collectors.toList());
			} else {
				nextNodes = nextNodes
						.stream().map(n -> nodeMap.get(n).get(1)).collect(Collectors.toList());
			}
			
			if (nextNodes.stream().filter(n -> "Z".equals(n.substring(2))).count() == nextNodes.size()) {
				destinationFound = true;
			}
			
			if (pathIndex == path.length() - 1) {
				pathIndex = 0;
			} else {
				pathIndex++;
			}
			
		}

		return steps;
	}
	
	/*
	 * THANKS TO THE REDDIT AOC 2023 COMMUNITY.
	 */
	public long determineStepsToDestinationWithLCM(String path, List<String> starts, Map<String, List<String>> nodeMap) {
		int pathIndex = 0;
		long steps;
		boolean destinationFound;
		List<Long> allSteps = new ArrayList<>();
		String nextNode;
		
		for (String start : starts) {
			steps = 0;
			destinationFound = false;
			nextNode = start;
			
			while (!destinationFound) {
				steps++;
				
				if (path.charAt(pathIndex) == 'L') {
					nextNode = nodeMap.get(nextNode).get(0);
				} else {
					nextNode = nodeMap.get(nextNode).get(1);
				}
				
				if ("Z".equals(nextNode.substring(2))) {
					destinationFound = true;
				}
				
				if (pathIndex == path.length() - 1) {
					pathIndex = 0;
				} else {
					pathIndex++;
				}
			}
			
			allSteps.add(steps);
		}

		return calculateLeastCommonMultiplierForListOfValues(allSteps);
	}
	
	public long calculateGreatestCommonDivisor(long a, long b) {
		while (a != b) {
			if (a < b) {
				b -= a;
			} else if (a > b) {
				a -= b;
			} else {
				return a;
			}
		}
		
		return a;
	}
	
	public long calculateLeastCommonMultiplier(long a, long b) {
		return Math.abs(a * b) / calculateGreatestCommonDivisor(a, b);
	}
	
	public long calculateLeastCommonMultiplierForListOfValues(List<Long> values) {
		List<Long> lcms = new ArrayList<>(Arrays.asList(calculateLeastCommonMultiplier(values.get(0), values.get(1))));
		
		for (int i = 2; i < values.size(); i++) {
			lcms.add(calculateLeastCommonMultiplier(lcms.get(i - 2), values.get(i)));
		}
		
		return lcms.stream().max(Comparator.comparing(Long::valueOf)).get();
	}
	
}
