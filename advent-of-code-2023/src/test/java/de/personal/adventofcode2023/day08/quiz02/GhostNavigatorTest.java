package de.personal.adventofcode2023.day08.quiz02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import de.personal.adventofcode2023.day08.quiz01.Node;

public class GhostNavigatorTest {

	private GhostNavigator navi;
	private String testPath;
	private List<Node> testData;

	@BeforeEach
	public void setup() {
		this.navi = new GhostNavigator();
		this.testPath = "LR";
		this.testData = new ArrayList<>();
		this.testData.addAll(Arrays.asList(new Node("11A", "11B", "XXX"), new Node("11B", "XXX", "11Z"),
				new Node("11Z", "11B", "XXX"), new Node("22A", "22B", "XXX"), new Node("22B", "22C", "22C"),
				new Node("22C", "22Z", "22Z"), new Node("22Z", "22B", "22B"), new Node("XXX", "XXX", "XXX")));
	}

	@Disabled
	@Test
	public void testDetermineStepsToDestinationWithBruteForce() {
		List<String> nodesStartingWithATest = this.navi.getAllNodesStartingWithA(this.testData);
		Map<String, List<String>> nodeMapTest = this.navi.transformNodeListToMap(this.testData);
		Assertions.assertEquals(6, this.navi.determineStepsToDestinationWithBruteForce(this.testPath,
				nodesStartingWithATest, nodeMapTest));

		List<Node> allNodes = this.navi.importAllNodes();
		List<String> nodesStartingWithAReal = this.navi.getAllNodesStartingWithA(allNodes);
		Map<String, List<String>> nodeMapReal = this.navi.transformNodeListToMap(allNodes);

		// Takes to long, but works (see test case)
		Assertions.assertEquals(11795205644011L,
				this.navi.determineStepsToDestinationWithBruteForce(this.navi.getPath(), nodesStartingWithAReal, nodeMapReal));
	}

	@Test
	public void testDetermineStepsToDestinationWithLCM() {
		List<String> nodesStartingWithATest = this.navi.getAllNodesStartingWithA(this.testData);
		Map<String, List<String>> nodeMapTest = this.navi.transformNodeListToMap(this.testData);
		Assertions.assertEquals(6,
				this.navi.determineStepsToDestinationWithLCM(this.testPath, nodesStartingWithATest, nodeMapTest));

		List<Node> allNodes = this.navi.importAllNodes();
		List<String> nodesStartingWithAReal = this.navi.getAllNodesStartingWithA(allNodes);
		Map<String, List<String>> nodeMapReal = this.navi.transformNodeListToMap(allNodes);

		Assertions.assertEquals(11795205644011L,
				this.navi.determineStepsToDestinationWithLCM(this.navi.getPath(),
						nodesStartingWithAReal, nodeMapReal));
	}

	@Test
	public void testCalculateGreatestCommonDivisor() {
		Assertions.assertEquals(12, this.navi.calculateGreatestCommonDivisor(84, 132));
	}

	@Test
	public void testCalculateLeastCommonMultiplier() {
		Assertions.assertEquals(42, this.navi.calculateLeastCommonMultiplier(21, 6));
	}

	@Test
	public void testCalculateLeastCommonMultiplierForListOfValues() {
		List<Long> valuesSmall = new ArrayList<>(Arrays.asList(2L, 3L));
		Assertions.assertEquals(6, this.navi.calculateLeastCommonMultiplierForListOfValues(valuesSmall));

		List<Long> valuesLarge = new ArrayList<>(Arrays.asList(12643L, 14257L, 15871L, 16409L, 18023L, 19637L));
		Assertions.assertEquals(11795205644011L,
				this.navi.calculateLeastCommonMultiplierForListOfValues(valuesLarge));
	}
}