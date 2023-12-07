package de.personal.adventofcode2023.day07.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Hands {

	private Hands() {}
	
	public static List<Hand> importAllHands(String inputPath) {
		List<Hand> allHands = new ArrayList<>();
		String line;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			while ((line = br.readLine()) != null) {
				allHands.add(new Hand(line.split(" ")[0], Integer.parseInt(line.split(" ")[1].trim())));
			}
		} catch (IOException e) {
			System.err.println("Error during data import.");
		}
		
		return allHands;
	}
	
	public static void swapValues(List<Hand> hands, int firstIndex, int secondIndex) {
		Hand tempHand = hands.get(firstIndex);
		hands.set(firstIndex, hands.get(secondIndex));
		hands.set(secondIndex, tempHand);
	}
	
	public static List<Hand> bubbleSortTheHandsByType(List<Hand> unsortedHands) {
		for (int i = 1; i < unsortedHands.size(); i++) {
			for (int j = 0; j < unsortedHands.size() - 1; j++) {
				if (unsortedHands.get(j).getType() > unsortedHands.get(j + 1).getType()) {
					swapValues(unsortedHands, j, j + 1);
				} 
			}
		}
		
		Collections.reverse(unsortedHands);
		return unsortedHands;
	}
	
	public static List<Hand> bubbleSortTheHandsByStrengths(List<Hand> sortedHands, int index) {
		List<Boolean> temp = new ArrayList<>();
		
		for (int i = 0; i < sortedHands.size(); i++) {
			for (int j = 0; j < sortedHands.size() - 1; j++) {
				if (sortedHands.get(j).getType() == sortedHands.get(j + 1).getType()) {
					if (index == 0) {
						if (sortedHands.get(j).getStrengths().get(index)
								> sortedHands.get(j + 1).getStrengths().get(index)) {
							swapValues(sortedHands, j, j + 1);
						}
						
					} else {
						for (int x = 0; x < index; x++) {
							temp.add(sortedHands.get(j).getStrengths().get(x)
									== sortedHands.get(j + 1).getStrengths().get(x));
						}
						
						if (!temp.contains(false)) {
							if (sortedHands.get(j).getStrengths().get(index)
									> sortedHands.get(j + 1).getStrengths().get(index)) {
								swapValues(sortedHands, j, j + 1);
							}
						}
						
						temp.clear();
					}
				} 
			}
		}
		
		return sortedHands;
	}
	
	public static long calculateSumOfBidTimesRank(List<Hand> sortedHands) {
		int sum = 0;
		
		for (int i = 1; i <= sortedHands.size(); i++) {
			sum += sortedHands.get(i - 1).getBid() * i;
		}
		return sum;
	}
	
	public static List<Character> getDistinctValues(Hand hand) {
		Set<Character> distinctValues = new HashSet<>();
		
		for (int i = 0; i < hand.getValues().length(); i++) {
			distinctValues.add(hand.getValues().charAt(i));
		}
		
		return distinctValues.stream().collect(Collectors.toList());
	}
	
	public static List<Integer> countValueOccurrences(Hand hand, List<Character> distinctValues) {
		List<Integer> occurrences = new ArrayList<>();
		int count;
		
		for (int i = 0; i < distinctValues.size(); i++) {
			count = 0;
			for (int j = 0; j < hand.getValues().length(); j++) {
				if (distinctValues.get(i) == hand.getValues().charAt(j)) {
					count++;
				}
			}
			
			occurrences.add(count);
		}
		
		return occurrences;
	}
	
	public static void determineTypeOfCard(Hand hand, List<Integer> occurences) {
		if (occurences.contains(5)) {
			hand.setType(1);
		} else if (occurences.contains(4)) {
			hand.setType(2);
		} else if (occurences.contains(3) && occurences.contains(2)) {
			hand.setType(3);
		} else if (occurences.contains(3) && occurences.contains(1)) {
			hand.setType(4);
		} else if (occurences.size() == 3 && occurences.contains(2) && occurences.contains(1)) {
			hand.setType(5);
		} else if (occurences.size() == 4 && occurences.contains(2) && occurences.contains(2)) {
			hand.setType(6);
		} else if (occurences.size() == 5) {
			hand.setType(7);
		} 
	}
	
	public static List<Integer> determineStrengthOfValues(Hand hand) {
		List<Integer> strengths = new ArrayList<>();
		
		for (int i = 0; i < hand.getValues().length(); i++) {
			
			if (Character.isDigit(hand.getValues().charAt(i))) {
				strengths.add(Integer.parseInt(String.valueOf(hand.getValues().charAt(i))));
			} else {
				switch (hand.getValues().charAt(i)) {
				case 'T':
					strengths.add(10);
					break;
				case 'J':
					strengths.add(11);
					break;
				case 'Q':
					strengths.add(12);
					break;
				case 'K':
					strengths.add(13);
					break;
				case 'A':
					strengths.add(14);
					break;
				}
			}
		}
		
		return strengths;
	}
}