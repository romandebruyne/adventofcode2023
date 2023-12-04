package de.personal.adventofcode2023.day04.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ScratchCardSolver {
	private final static String INPUT_PATH = "./res/aoc_day04_input.txt";
	private final List<String> allLines = new ArrayList<>();
	private final Map<Integer, List<Integer>> numbersToCompare = new HashMap<>();
	private final Map<Integer, List<Integer>> winningNumbers = new HashMap<>();
	
	public ScratchCardSolver() {
		importAllLines();
	}
	
	public int calculateSumOfPoints(Map<Integer, List<Integer>> numbersToCompare,
			Map<Integer, List<Integer>> winningNumbers) {
		int sumOfPoints = 0, pointsPerGame;
		
		for (Entry<Integer, List<Integer>> numbersComp : numbersToCompare.entrySet()) {
			pointsPerGame = 0;
			for (int numberComp : numbersComp.getValue()) {
				if (winningNumbers.get(numbersComp.getKey()).contains(numberComp)) {
					if (pointsPerGame == 0) {
						pointsPerGame++;
					} else {
						pointsPerGame *= 2;
					}
				}
			}
			sumOfPoints += pointsPerGame;
		}
		
		return sumOfPoints;
	}
	
	public void importAllLines() {
		String line;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(INPUT_PATH))) {
			while ((line = br.readLine()) != null) {
				this.allLines.add(line);
			}
			
		} catch (IOException e) {
			System.err.println("Error occurred during data import.");
		}
	}
	
	public void createNumberMaps(List<String> allLines) {
		String[] numbersToCompare, winningNumbers;
		int gameId;
		
		for (String line : allLines) {
			gameId = Integer.parseInt(line.split("\\|")[0].split(":")[0].trim().split("Card")[1].trim());
			numbersToCompare = line.split("\\|")[0].split(":")[1].split(" ");
			winningNumbers = line.split("\\|")[1].split(" ");
			
			for (String number : numbersToCompare) {
				if (this.numbersToCompare.containsKey(gameId)) {
					try {
						this.numbersToCompare.get(gameId).add(Integer.parseInt(number.trim()));
					} catch (Exception e) {
						System.err.println("Error during parsing");
					}
				} else {
					this.numbersToCompare.put(gameId, new ArrayList<>());
				}
			}
			
			for (String number : winningNumbers) {
				if (this.winningNumbers.containsKey(gameId)) {
					try {
						this.winningNumbers.get(gameId).add(Integer.parseInt(number.trim()));
					} catch (Exception e) {
						System.err.println("Error during parsing");
					}
				} else {
					this.winningNumbers.put(gameId, new ArrayList<>());
				}
			}
		}
	}
	
	public List<String> getAllLines() {
		return this.allLines;
	}
	
	public Map<Integer, List<Integer>> getNumbersToCompare() {
		return this.numbersToCompare;
	}
	
	public Map<Integer, List<Integer>> getWinnnigNumbers() {
		return this.winningNumbers;
	}
}
