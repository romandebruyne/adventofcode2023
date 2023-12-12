package de.personal.adventofcode2023.day12.quiz01;

import java.util.List;

public class Record {
	
	private String parts;
	private List<Integer> sizes;
	
	public Record(String parts, List<Integer> sizes) {
		this.parts = parts;
		this.sizes = sizes;
	}

	public String getParts() {
		return this.parts;
	}

	public List<Integer> getSizes() {
		return this.sizes;
	}
}
