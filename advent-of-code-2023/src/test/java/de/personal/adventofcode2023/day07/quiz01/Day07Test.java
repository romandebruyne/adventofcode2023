package de.personal.adventofcode2023.day07.quiz01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day07Test {
	
	private List<Hand> testData;
	
	@BeforeEach
	public void setup() {
		this.testData = new ArrayList<>();
		this.testData.addAll(Arrays.asList(new Hand("32T3K", 765), new Hand("T55J5", 684),
				new Hand("KK677", 28), new Hand("KTJJT", 220), new Hand("QQQJA", 483)));
	}
	
	@Test
	public void testDetermineStrengthOfCard() {
		for (Hand h : this.testData) {
			List<Character> dist = Hands.getDistinctValues(h);
			List<Integer> occ = Hands.countValueOccurrences(h, dist);
			Hands.determineTypeOfCard(h, occ);
			Hands.determineStrengthOfValues(h);
		}
		
		List<Hand> sortedHands = Hands.bubbleSortTheHandsByType(this.testData);
		List<Hand> sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedHands, 0);
		
		for (int i = 1; i < 5; i++) {
			sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedSortedHands, 1);
			sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedSortedHands, 2);
			sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedSortedHands, 3);
			sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedSortedHands, 4);
			
		}
		
		Assertions.assertEquals(6440, Hands.calculateSumOfBidTimesRank(sortedSortedHands));
	}
	
	@Test
	public void testDetermineStrengthOfCardWithRealInput() {
		List<Hand> hands = Hands.importAllHands("./res/aoc_day07_input.txt");
		
		for (Hand h : hands) {
			List<Character> dist = Hands.getDistinctValues(h);
			List<Integer> occ = Hands.countValueOccurrences(h, dist);
			Hands.determineTypeOfCard(h, occ);
			Hands.determineStrengthOfValues(h);
		}
		
		List<Hand> sortedHands = Hands.bubbleSortTheHandsByType(hands);
		List<Hand> sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedHands, 0);
		
		for (int i = 1; i < 5; i++) {
			sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedSortedHands, 1);
			sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedSortedHands, 2);
			sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedSortedHands, 3);
			sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedSortedHands, 4);
		}
		
		Assertions.assertEquals(246163188, Hands.calculateSumOfBidTimesRank(sortedSortedHands));
	}
}