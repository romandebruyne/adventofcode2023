package de.personal.adventofcode2023.day19.quiz01;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class Day19UtilsTest {
	
	private Map<String, Workflow> testWorkflows;
	private Map<String, Workflow> workflows;
	private List<MachinePart> testMachineParts;
	private List<MachinePart> machineParts;
	
	@BeforeEach
	public void setup() {
		this.testWorkflows = Day19Utils.importAllWorkflows("./res/aoc_day19_testinput.txt");
		this.workflows = Day19Utils.importAllWorkflows("./res/aoc_day19_input.txt");
		this.testMachineParts = Day19Utils.importAllMachinePart("./res/aoc_day19_testinput.txt");
		this.machineParts = Day19Utils.importAllMachinePart("./res/aoc_day19_input.txt");
	}
	
	@Disabled
	@Test
	public void testCheckMachineParts() {
		for (int i = 0; i < this.testMachineParts.size(); i++) {
			Day19Utils.checkMachineParts(this.testMachineParts.get(i), this.testWorkflows, "in");
			System.out.println(this.testMachineParts.get(i).isAccepted());
		}
	}
	
	@Test
	public void testGetAcceptedMachineParts() {
		Assertions.assertEquals(3, Day19Utils.getAcceptedMachineParts(this.testMachineParts,
				this.testWorkflows).size());
	}
	
	@Test
	public void testCalculateSumOfRatings() {
		Assertions.assertEquals(19114, 
				Day19Utils.calculateSumOfRatings(Day19Utils.getAcceptedMachineParts(this.testMachineParts,
				this.testWorkflows)));
		
		Assertions.assertEquals(432788, 
				Day19Utils.calculateSumOfRatings(Day19Utils.getAcceptedMachineParts(this.machineParts,
				this.workflows)));
	}
}
