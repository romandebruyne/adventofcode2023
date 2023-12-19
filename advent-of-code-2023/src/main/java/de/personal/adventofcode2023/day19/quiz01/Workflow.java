package de.personal.adventofcode2023.day19.quiz01;

import java.util.List;

public class Workflow {
	private String name;
	private List<String> rules;
	
	public Workflow(String name, List<String> rules) {
		this.name = name;
		this.rules = rules;
	}

	public String getName() {
		return this.name;
	}

	public List<String> getRules() {
		return this.rules;
	}

}
