package de.personal.adventofcode2023.day12.quiz01and02;

import java.util.ArrayList;
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
	
	public void transformParts(int multiplier) {
		String currentContent = this.parts;
		
		for (int i = 0; i < multiplier; i++) {
			this.parts = this.parts.concat("?" + currentContent);
		}
	}
	
	public void transformSizes(int multiplier) {
		List<Integer> currentContent = new ArrayList<>(this.sizes);
		
		for (int i = 0; i < multiplier; i++) {
			this.sizes.addAll(currentContent);
		}
	}
}
