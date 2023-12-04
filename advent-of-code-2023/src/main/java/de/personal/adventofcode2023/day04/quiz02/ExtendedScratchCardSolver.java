package de.personal.adventofcode2023.day04.quiz02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.personal.adventofcode2023.day04.quiz01.ScratchCardSolver;

public class ExtendedScratchCardSolver extends ScratchCardSolver {
	private final Map<Integer, List<Integer>> scratchcardsWonByScratchcardId = new HashMap<>();
	
	public int calculateSumOfScratchCards(Map<Integer, List<Integer>> numbersToCompare,
			Map<Integer, List<Integer>> winningNumbers) {
		List<Integer> allScratchcardsWon = new ArrayList<>();
		allScratchcardsWon.addAll(this.scratchcardsWonByScratchcardId.keySet());
		
		for (int id = 0; id < allScratchcardsWon.size(); id++) {
			allScratchcardsWon.addAll(this.scratchcardsWonByScratchcardId.get(allScratchcardsWon.get(id)));
		}

		return allScratchcardsWon.size();
	}
	
	public void determineScratchcardsWonByScratchcardId(Map<Integer, List<Integer>> numbersToCompare,
			Map<Integer, List<Integer>> winningNumbers) {
		int numberOfMatches;
		
		for (Entry<Integer, List<Integer>> numbersComp : numbersToCompare.entrySet()) {
			numberOfMatches = 0;
			
			for (int numberComp : numbersComp.getValue()) {
				if (winningNumbers.get(numbersComp.getKey()).contains(numberComp)) {
					numberOfMatches++;
				}
			}
			
			this.scratchcardsWonByScratchcardId.put(numbersComp.getKey(), new ArrayList<>());
			for (int i = 1; i < numberOfMatches + 1; i++) {
				this.scratchcardsWonByScratchcardId.get(numbersComp.getKey()).add(numbersComp.getKey() + i);
			}
		}			
	}
	
	public Map<Integer, List<Integer>> getScratchcardsWonByScratchcardId() {
		return this.scratchcardsWonByScratchcardId;
	}
}
