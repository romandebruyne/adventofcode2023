package de.personal.adventofcode2023.day04.quiz02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.personal.adventofcode2023.day04.quiz01.ScratchCardSolver;

public class ExtendedScratchCardSolver extends ScratchCardSolver {
	private final Map<Integer, List<Integer>> scratchcardsWonById = new HashMap<>();
	
	public int calculateSumOfScratchCards(Map<Integer, List<Integer>> numbersToCompare,
			Map<Integer, List<Integer>> winningNumbers) {
		List<Integer> allScratchcardsWon = new ArrayList<>();
		allScratchcardsWon.addAll(this.scratchcardsWonById.keySet());
		
		for (int id = 0; id < allScratchcardsWon.size(); id++) {
			try {
				allScratchcardsWon.addAll(this.scratchcardsWonById.get(allScratchcardsWon.get(id)));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return allScratchcardsWon.size();
	}
	
	public void determineScratchcardsWonById(Map<Integer, List<Integer>> numbersToCompare,
			Map<Integer, List<Integer>> winningNumbers) {
		int numberOfMatches;
		
		for (Entry<Integer, List<Integer>> numbersComp : numbersToCompare.entrySet()) {
			numberOfMatches = 0;
			
			for (int numberComp : numbersComp.getValue()) {
				if (winningNumbers.get(numbersComp.getKey()).contains(numberComp)) {
					numberOfMatches++;
				}
			}
			
			this.scratchcardsWonById.put(numbersComp.getKey(), new ArrayList<>());
			for (int i = 1; i < numberOfMatches + 1; i++) {
				this.scratchcardsWonById.get(numbersComp.getKey()).add(numbersComp.getKey() + i);
			}
		}			
	}
	
	public Map<Integer, List<Integer>> getScratchcardsWonById() {
		return this.scratchcardsWonById;
	}
}
