package de.personal.adventofcode2023.day19.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day19Utils {
	
	private Day19Utils() {}
	
	public static Map<String, Workflow> importAllWorkflows(String inputPath) {
		Map<String, Workflow> allWorkflows = new HashMap<>();
		Workflow tempWorkflow;
		String line;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				tempWorkflow = getWorkflowFromString(line);
				allWorkflows.put(tempWorkflow.getName(), tempWorkflow);
			}
			
		} catch (IOException e) {
			System.err.println("Error during workflows import.");
		}
		
		return allWorkflows;
	}
	
	private static Workflow getWorkflowFromString(String str) {
		if (str != null && !str.isEmpty()) {
			String workflowName = str.substring(0, str.indexOf("{"));
			List<String> rules = Arrays.asList(str.substring(str.indexOf("{") + 1, str.indexOf("}")).split(","));
			return new Workflow(workflowName, rules);
		} else {
			return null;
		}
	}
	
	public static List<MachinePart> importAllMachinePart(String inputPath) {
		List<MachinePart> allMachineParts = new ArrayList<>();
		Map<String, Long> ratings;
		String line;
		int emptyLineCount = 0;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			do {
				line = br.readLine();
				
				if (line == null || line.isEmpty()) {
					emptyLineCount++;
				}
				
				if (emptyLineCount == 1 && line != null && !line.isEmpty()) {
					ratings = new HashMap<>();
					
					for (String rating : line.substring(line.indexOf("{") + 1, line.indexOf("}")).split(",")) {
						ratings.put(rating.substring(0, rating.indexOf("=")),
								Long.parseLong(rating.substring(rating.indexOf("=") + 1)));
					}
					
					allMachineParts.add(new MachinePart(ratings));
				}
				
			} while (emptyLineCount < 2);
			
			
		} catch (IOException e) {
			System.err.println("Error during machine parts import.");
		}
		
		return allMachineParts;
	}
	
	public static String decide(MachinePart machinePart, String rule) {
		String machinePartCategory;
		long thresholdValue;
		String result = rule;
		
		if (rule.contains("<")) {
			machinePartCategory = rule.substring(0, rule.indexOf("<"));
			thresholdValue = Long.parseLong(rule.substring(rule.indexOf("<") + 1, rule.indexOf(":")));
			
			if (machinePart.getRatings().get(machinePartCategory) < thresholdValue) {
				return rule.substring(rule.indexOf(":") + 1);
			}
			
		} else if (rule.contains(">")) {
			machinePartCategory = rule.substring(0, rule.indexOf(">"));
			thresholdValue = Long.parseLong(rule.substring(rule.indexOf(">") + 1, rule.indexOf(":")));
			
			if (machinePart.getRatings().get(machinePartCategory) > thresholdValue) {
				return rule.substring(rule.indexOf(":") + 1);
			} 
		} else if ("A".equals(rule)) {
			return "A"; 
		} else if ("R".equals(rule)) {
			return "R";
		}
		
		return result;
	}
	
	public static void checkMachineParts(MachinePart machinePart, Map<String, Workflow> allWorkflows, String result) {
		List<String> rules;
		boolean newWorkflowFound = false;
		
		if ("in".equals(result)) {
			for (String rule : allWorkflows.get("in").getRules()) {
				result = decide(machinePart, rule);
				if (result.equals(rule)) {
					continue;
				} else if ("A".equals(result)) {
					machinePart.setAccepted(true);
					return;
				} else if ("R".equals(result)) {
					machinePart.setAccepted(false);
					return;
				} else {
					checkMachineParts(machinePart, allWorkflows, result);
					newWorkflowFound = true;
					break;
				}
			}
		}
		
		while (!newWorkflowFound) {
			if ("A".equals(result)) {
				machinePart.setAccepted(true);
				return;
			} else if ("R".equals(result)) {
				machinePart.setAccepted(false);
				return;
			} else {
				rules = allWorkflows.get(result).getRules();
			}
			
			for (String rule : rules) {
				result = decide(machinePart, rule);
				if (result.equals(rule)) {
					continue;
				} else if ("A".equals(result)) {
					machinePart.setAccepted(true);
					return;
				} else if ("R".equals(result)) {
					machinePart.setAccepted(false);
					return;
				} else {
					checkMachineParts(machinePart, allWorkflows, result);
					newWorkflowFound = true;
					break;
				}
			}
		}
	}
	
	public static List<MachinePart> getAcceptedMachineParts(List<MachinePart> allMachineParts,
			Map<String, Workflow> allWorkflows) {
		for (MachinePart mp : allMachineParts) {
			checkMachineParts(mp, allWorkflows, "in");
		}
		
		return allMachineParts.stream().filter(mp -> mp.isAccepted()).collect(Collectors.toList());
	}
	
	public static long calculateSumOfRatings(List<MachinePart> acceptedMachineParts) {
		return acceptedMachineParts.stream().map(mp -> mp.getRatings().values().stream().reduce(0L, (a, b) -> a + b))
			.reduce(0L, (a, b) -> a + b);
	}
}
