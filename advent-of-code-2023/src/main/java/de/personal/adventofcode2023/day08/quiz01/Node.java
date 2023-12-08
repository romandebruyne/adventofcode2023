package de.personal.adventofcode2023.day08.quiz01;

public class Node {
	
	private String label;
	private String leftInstruction;
	private String rightInstruction;
	
	public Node(String label, String leftInstruction, String rightInstruction) {
		this.label = label;
		this.leftInstruction = leftInstruction;
		this.rightInstruction = rightInstruction;
	}

	public String getLabel() {
		return this.label;
	}

	public String getLeftInstruction() {
		return this.leftInstruction;
	}

	public String getRightInstruction() {
		return this.rightInstruction;
	}
}
