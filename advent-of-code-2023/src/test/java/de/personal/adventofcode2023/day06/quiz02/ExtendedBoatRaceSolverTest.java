package de.personal.adventofcode2023.day06.quiz02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExtendedBoatRaceSolverTest {
	
	private ExtendedBoatRaceSolver ebrs;
	private long testTime = 71530L;
	private long testDistance = 940200L;
	
	@BeforeEach
	public void setup() {
		this.ebrs = new ExtendedBoatRaceSolver();
	}
	
	@Test
	@DisplayName("Get potential wins")
	public void testGetPotentialWins() {
		Assertions.assertEquals(71503, this.ebrs.getPotentialWins(this.testTime, this.testDistance));
		Assertions.assertEquals(39132886, this.ebrs.getPotentialWins(this.ebrs.getTime(), this.ebrs.getDistance()));
	}
}