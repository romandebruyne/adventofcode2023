package de.personal.adventofcode2023.day02.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CubeGameSolver {
	private final static String INPUT_PATH = "./res/aoc_day02_input.txt";
	private final Map<Integer, Map<Integer, Map<String, Integer>>> allGames = new HashMap<>();
	private final Map<String, Integer> possibleGames = new HashMap<>();
	
	public CubeGameSolver() {
		importAllGames();
		definePossibleGames();
	}
	
	public int calculateSumOfPossibleGameIds() {
		int sumOfPossibleGameIds = 0;
		
		for (Entry<Integer, Map<Integer, Map<String, Integer>>> game : this.allGames.entrySet()) {
			if (isGamePossible(game.getValue())) {
				sumOfPossibleGameIds += game.getKey();
			}
		}
		
		return sumOfPossibleGameIds;
	}
	
	public boolean isGamePossible(Map<Integer, Map<String, Integer>> gameSets) {
		for (Map<String, Integer> gameSet : gameSets.values()) {
			if (gameSet.containsKey("blue")) {
				if (gameSet.get("blue") > this.possibleGames.get("blue")) {
					return false;
				}
			}
			
			if (gameSet.containsKey("red")) {
				if (gameSet.get("red") > this.possibleGames.get("red")) {
					return false;
				}
			}
			
			if (gameSet.containsKey("green")) {
				if (gameSet.get("green") > this.possibleGames.get("green")) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void importAllGames() {
		String line;
		String[] lineArray;
		int gameId;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(INPUT_PATH))) {
			while ((line = br.readLine()) != null) {
				lineArray = line.split(":");
				gameId = getGameId(lineArray[0].trim());
				this.allGames.put(gameId, getSetData(lineArray[1].trim()));
			}
			
		} catch (IOException e) {
			System.err.println("Error occurred during game data import.");
		}
	}
	
	public void definePossibleGames() {
		this.possibleGames.put("blue", 14);
		this.possibleGames.put("red", 12);
		this.possibleGames.put("green", 13);
	}
	
	public int getGameId(String input) {
		return Integer.parseInt(input.split(" ")[1]);
	}
	
	public Map<Integer, Map<String, Integer>> getSetData(String input) {
		Map<Integer, Map<String, Integer>> resultMap = new HashMap<>();
		String[] cubeSetArray = input.split(";");
		int setId = 0;
		
		for (String cubeSet : cubeSetArray) {
			setId++;
			resultMap.put(setId, getColorAndCount(cubeSet.trim()));
		}
		
		return resultMap;
	}
	
	public Map<String, Integer> getColorAndCount(String cubeSet) { 
		Map<String, Integer> resultMap = new HashMap<>();
		String color;
		int count;
		
		for (String cubeData : cubeSet.split(",")) {
			color = cubeData.trim().split(" ")[1];
			count = Integer.parseInt(cubeData.trim().split(" ")[0]);
			resultMap.put(color, count);
		}
		
		return resultMap;
	}
	
	public Map<Integer, Map<Integer, Map<String, Integer>>> getAllGames() {
		return this.allGames;
	}
}
