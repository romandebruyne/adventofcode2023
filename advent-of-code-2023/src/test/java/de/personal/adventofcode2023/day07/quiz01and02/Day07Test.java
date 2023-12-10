package de.personal.adventofcode2023.day07.quiz01and02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Day07Test {
	
	private List<Hand> testDataPartOne;
	// private List<Hand> testDataPartTwo;
	
	@BeforeEach
	public void setup() {
		this.testDataPartOne = new ArrayList<>();
		this.testDataPartOne.addAll(Arrays.asList(new Hand("32T3K", 765), new Hand("T55J5", 684),
				new Hand("KK677", 28), new Hand("KTJJT", 220), new Hand("QQQJA", 483)));
	}
	
	@Test
	@DisplayName("Determine strength of card, test with test data (part one)")
	public void testDetermineStrengthOfCardPartOne() {
		for (Hand h : this.testDataPartOne) {
			List<Character> dist = Hands.getDistinctValues(h);
			Map<Character, Integer> occ = Hands.countValueOccurrences(h, dist);
			Hands.determineTypeOfCard(h, occ);
			Hands.determineStrengthOfValuesPartOne(h);
		}
		
		List<Hand> sortedHands = Hands.bubbleSortTheHandsByType(this.testDataPartOne);
		List<Hand> sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedHands, 0);
		
		for (int i = 1; i < 5; i++) {
			sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedSortedHands, i);
			
		}
		
		Assertions.assertEquals(6440, Hands.calculateSumOfBidTimesRank(sortedSortedHands));
	}
	
	@Test
	@DisplayName("Determine strength of card, test with test data (part two)")
	public void testDetermineStrengthOfCardPartTwo() {
		for (Hand h : this.testDataPartOne) {
			List<Character> dist = Hands.getDistinctValues(h);
			Map<Character, Integer> occ = Hands.countValueOccurrences(h, dist);
			Hands.determineTypeOfCardWithJokerReplacement(h, occ);
			Hands.determineStrengthOfValuesPartTwo(h);
		}
		
		List<Hand> sortedHands = Hands.bubbleSortTheHandsByType(this.testDataPartOne);
		List<Hand> sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedHands, 0);
		
		for (int i = 1; i < 5; i++) {
			sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedSortedHands, i);
			
		}
		
		Assertions.assertEquals(5905, Hands.calculateSumOfBidTimesRank(sortedSortedHands));
	}
	
	@Test
	@DisplayName("Determine strength of card, test with real data (part one)")
	public void testDetermineStrengthOfCardWithRealInputPartOnes() {
		List<Hand> hands = Hands.importAllHands("./res/aoc_day07_testinput.txt");
		
		for (Hand h : hands) {
			List<Character> dist = Hands.getDistinctValues(h);
			Map<Character, Integer> occ = Hands.countValueOccurrences(h, dist);
			Hands.determineTypeOfCard(h, occ);
			Hands.determineStrengthOfValuesPartOne(h);
		}
		
		List<Hand> sortedHands = Hands.bubbleSortTheHandsByType(hands);
		List<Hand> sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedHands, 0);
		
		for (int i = 1; i < 5; i++) {
			sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedSortedHands, i);
		}
		
		Assertions.assertEquals(6592, Hands.calculateSumOfBidTimesRank(sortedSortedHands));
	}
	
	@Test
	@DisplayName("Determine strength of card, test with real data (part one)")
	public void testDetermineStrengthOfCardWithRealInputPartOne() {
		List<Hand> hands = Hands.importAllHands("./res/aoc_day07_input.txt");
		
		for (Hand h : hands) {
			List<Character> dist = Hands.getDistinctValues(h);
			Map<Character, Integer> occ = Hands.countValueOccurrences(h, dist);
			Hands.determineTypeOfCard(h, occ);
			Hands.determineStrengthOfValuesPartOne(h);
		}
		
		List<Hand> sortedHands = Hands.bubbleSortTheHandsByType(hands);
		List<Hand> sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedHands, 0);
		
		for (int i = 1; i < 5; i++) {
			sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedSortedHands, i);
		}

		Assertions.assertEquals(246163188, Hands.calculateSumOfBidTimesRank(sortedSortedHands));
	}
	
	@Test
	@DisplayName("Determine strength of card, test with real data (part two)")
	public void testDetermineStrengthOfCardWithRealInputPartTwo() {
		List<Hand> hands = Hands.importAllHands("./res/aoc_day07_input.txt");
		
		for (Hand h : hands) {
			List<Character> dist = Hands.getDistinctValues(h);
			Map<Character, Integer> occ = Hands.countValueOccurrences(h, dist);
			Hands.determineTypeOfCardWithJokerReplacement(h, occ);
			Hands.determineStrengthOfValuesPartTwo(h);
		}
		
		List<Hand> sortedHands = Hands.bubbleSortTheHandsByType(hands);
		List<Hand> sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedHands, 0);
		
		for (int i = 1; i < 5; i++) {
			sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedSortedHands, i);
		}
		
		Assertions.assertEquals(245794069, Hands.calculateSumOfBidTimesRank(sortedSortedHands));
	}
	
	@Test
	@DisplayName("Determine strength of card, test with real data (part two)")
	public void testDetermineStrengthOfCardWithRealInputPartTwos() {
		List<Hand> hands = Hands.importAllHands("./res/aoc_day07_testinput.txt");
		
		for (Hand h : hands) {
			List<Character> dist = Hands.getDistinctValues(h);
			Map<Character, Integer> occ = Hands.countValueOccurrences(h, dist);
			Hands.determineTypeOfCardWithJokerReplacement(h, occ);
			Hands.determineStrengthOfValuesPartTwo(h);
		}
		
		List<Hand> sortedHands = Hands.bubbleSortTheHandsByType(hands);
		List<Hand> sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedHands, 0);
		
		for (int i = 1; i < 5; i++) {
			sortedSortedHands = Hands.bubbleSortTheHandsByStrengths(sortedSortedHands, i);
		}
		
		Assertions.assertEquals(6839, Hands.calculateSumOfBidTimesRank(sortedSortedHands));
	}
}