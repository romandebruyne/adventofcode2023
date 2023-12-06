package de.personal.adventofcode2023.day06.quiz01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoatRaceSolverTest {
	
	private BoatRaceSolver brs;
	private List<Integer> testTimes = new ArrayList<>(Arrays.asList(7, 15, 30));
	private List<Integer> testdistances = new ArrayList<>(Arrays.asList(9, 40, 200));
	
	@BeforeEach
	public void setup() {
		this.brs = new BoatRaceSolver();
	}
	
	@Test
	@DisplayName("Get potentials")
	public void testPot() {
		Assertions.assertEquals(288, this.brs.getPotentialWins(this.testTimes, this.testdistances));
		Assertions.assertEquals(2374848, this.brs.getPotentialWins(
				this.brs.getAllTimes(), this.brs.getAllDistances()));
	}
}