package de.personal.adventofcode2023.day08.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Navigator {
	private String path;
	
	public List<Node> importAllNodes() {
		String line, label, leftInstruction, rightInstruction;
		List<Node> allNodes = new ArrayList<>();
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get("./res/aoc_day08_input.txt"))) {
			this.path = br.readLine().trim();
			br.readLine();			// skip empty line
			
			while ((line = br.readLine()) != null) {
				label = line.substring(0, line.indexOf("=") - 1).trim();
				leftInstruction = line.substring(line.indexOf("(") + 1, line.indexOf(",")).trim();
				rightInstruction = line.substring(line.indexOf(",") + 2, line.indexOf(")")).trim();
				allNodes.add(new Node(label, leftInstruction, rightInstruction));
				
			}
		} catch (IOException e) {
			System.err.println("Error during data import.");
			
		}
		
		return allNodes;
	}
	
	public Map<String, List<String>> transformNodeListToMap(List<Node> allNodes) {
		return allNodes.stream()
				.collect(Collectors.toMap(Node::getLabel,
						n -> new ArrayList<>(Arrays.asList(n.getLeftInstruction(), n.getRightInstruction()))));
	}
	
	public int determineStepsToDestination(String path, String start, Map<String, List<String>> nodeMap) {
		int steps = 0, pathIndex = 0;
		boolean destinationFound = false;
		String nextNode = start;
		
		while (!destinationFound) {
			steps++;
			
			if (path.charAt(pathIndex) == 'L') {
				nextNode = nodeMap.get(nextNode).get(0);
			} else {
				nextNode = nodeMap.get(nextNode).get(1);
			}
			
			if ("ZZZ".equals(nextNode)) {
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
	
	
	public String getPath() {
		return this.path;
	}

}
