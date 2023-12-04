package de.personal.adventofcode2023.day04.quiz01;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScratchCardSolverTest {
	private ScratchCardSolver scs;
	private List<String> testData;

	@BeforeEach
	public void setup() {
		System.out.println("ScratchCardSolver Test starts");
		this.scs = new ScratchCardSolver();
		this.testData = new ArrayList<>();
		this.testData.add("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53");
		this.testData.add("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19");
		this.testData.add("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1");
		this.testData.add("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83");
		this.testData.add("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36");
		this.testData.add("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11");
	}

	@Test
	@DisplayName("Calculate sum of points")
	public void testCalculateSumOfPoints() {
		this.scs.createNumberMaps(this.testData);
		Assertions.assertEquals(13, 
				this.scs.calculateSumOfPoints(this.scs.getNumbersToCompare(), this.scs.getWinnnigNumbers()));
		
		this.scs = new ScratchCardSolver();
		this.scs.createNumberMaps(this.scs.getAllLines());
		Assertions.assertEquals(19855, 
				this.scs.calculateSumOfPoints(this.scs.getNumbersToCompare(), this.scs.getWinnnigNumbers()));
	}
}
