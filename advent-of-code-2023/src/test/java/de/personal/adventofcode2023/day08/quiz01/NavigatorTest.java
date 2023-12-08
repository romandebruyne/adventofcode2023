package de.personal.adventofcode2023.day08.quiz01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NavigatorTest {
	private Navigator navi;
	private String testPath;
	private List<Node> testData;
	
	@BeforeEach
	public void setup() {
		this.navi = new Navigator();
		this.testPath = "RL";
		this.testData = new ArrayList<>();
		this.testData.addAll(Arrays.asList(
				new Node("AAA", "BBB", "CCC"),
				new Node("BBB", "DDD", "EEE"),
				new Node("CCC", "ZZZ", "GGG"),
				new Node("DDD", "DDD", "DDD"),
				new Node("EEE", "EEE", "EEE"),
				new Node("GGG", "GGG", "GGG"),
				new Node("ZZZ", "ZZZ", "ZZZ")));
		
//		this.testPath = "LLR";
//		this.testData = new ArrayList<>();
//		this.testData.addAll(Arrays.asList(
//				new Node("AAA", "BBB", "BBB"),
//				new Node("BBB", "AAA", "ZZZ"),
//				new Node("ZZZ", "ZZZ", "ZZZ")));
	}
	
	@Test
	public void testDetermineStepsToDestination() {
		Map<String, List<String>> nodeMapTest = this.navi.transformNodeListToMap(this.testData);
		Assertions.assertEquals(2,  this.navi.determineStepsToDestination(this.testPath, "AAA", nodeMapTest));
		
		Map<String, List<String>> nodeMapReal = this.navi.transformNodeListToMap(this.navi.importAllNodes());
		Assertions.assertEquals(16409, this.navi.determineStepsToDestination(this.navi.getPath(), "AAA", nodeMapReal));
	}
}
