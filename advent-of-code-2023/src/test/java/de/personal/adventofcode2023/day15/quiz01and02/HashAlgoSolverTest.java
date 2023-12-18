package de.personal.adventofcode2023.day15.quiz01and02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HashAlgoSolverTest {
	
	private String testString = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7";
	private List<String> testData;
	private List<String> realData;
	
	@BeforeEach
	public void setup() {
		this.testData = new ArrayList<>();
		this.testData.addAll(Arrays.asList(this.testString.split(",")));

		this.realData = new ArrayList<>();
		this.realData = HashAlgoSolver.importAllData("./res/aoc_day15_input.txt");
	}
	
	@Test
	public void testApplyHashAlgorithm() {
		Assertions.assertEquals(52, HashAlgoSolver.applyHashAlgorithm("HASH"));
	}
	
	@Test
	public void testCalculateSumPartOne() {
		Assertions.assertEquals(1320, HashAlgoSolver.calculateSumPartOne(this.testData));
		Assertions.assertEquals(516657, HashAlgoSolver.calculateSumPartOne(this.realData));
	}
	
	@Test
	public void testCalculateSumPartTwo() {
		Assertions.assertEquals(145, HashAlgoSolver.calculateSumPartTwo(this.testData));
		Assertions.assertEquals(210906, HashAlgoSolver.calculateSumPartTwo(this.realData));
	}
}
