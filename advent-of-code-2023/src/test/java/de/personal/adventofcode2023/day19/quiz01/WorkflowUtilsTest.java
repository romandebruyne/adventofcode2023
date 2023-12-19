package de.personal.adventofcode2023.day19.quiz01;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class WorkflowUtilsTest {
	
	private Map<String, Workflow> testWorkflows;
	private Map<String, Workflow> workflows;
	private List<MachinePart> testMachineParts;
	private List<MachinePart> machineParts;
	
	@BeforeEach
	public void setup() {
		this.testWorkflows = WorkflowUtils.importAllWorkflows("./res/aoc_day19_testinput.txt");
		this.workflows = WorkflowUtils.importAllWorkflows("./res/aoc_day19_input.txt");
		this.testMachineParts = WorkflowUtils.importAllMachinePart("./res/aoc_day19_testinput.txt");
		this.machineParts = WorkflowUtils.importAllMachinePart("./res/aoc_day19_input.txt");
	}
	
	@Disabled
	@Test
	public void testCheckMachineParts() {
		for (int i = 0; i < this.testMachineParts.size(); i++) {
			WorkflowUtils.checkMachineParts(this.testMachineParts.get(i), this.testWorkflows, "in");
			System.out.println(this.testMachineParts.get(i).isAccepted());
		}
	}
	
	@Test
	public void testGetAcceptedMachineParts() {
		Assertions.assertEquals(3, WorkflowUtils.getAcceptedMachineParts(this.testMachineParts,
				this.testWorkflows).size());
	}
	
	@Test
	public void testCalculateSumOfRatings() {
		Assertions.assertEquals(19114, 
				WorkflowUtils.calculateSumOfRatings(WorkflowUtils.getAcceptedMachineParts(this.testMachineParts,
				this.testWorkflows)));
		
		Assertions.assertEquals(432788, 
				WorkflowUtils.calculateSumOfRatings(WorkflowUtils.getAcceptedMachineParts(this.machineParts,
				this.workflows)));
	}
}
