package de.personal.adventofcode2023.day15.quiz01and02;

public class Lens {
	private String label;
	private long hashValue;
	private char operator;
	private long focalLength;
	
	public Lens(String label, char operator, long focalLength) {
		this.label = label;
		this.hashValue = HashAlgoSolver.applyHashAlgorithm(label);
		this.operator = operator;
		this.focalLength = focalLength;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public long getHashValue() {
		return this.hashValue;
	}
	
	public char getOperator() {
		return this.operator;
	}
	
	public long getFocalLength() {
		return focalLength;
	}
	
	@Override
	public String toString() {
		return this.label + " " + this.focalLength;
	}
}
