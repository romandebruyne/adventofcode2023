package de.personal.adventofcode2023.day13.quiz01;

import java.util.List;

public class Pattern {
	
	private List<String> patternContent;
	private long columnIndex;
	private long rowIndex;
	
	public Pattern(List<String> patternContent, long columnValue, long rowValue) {
		this.patternContent = patternContent;
		this.columnIndex = columnValue;
		this.rowIndex = rowValue;
	}
	
	public Pattern(List<String> patternContent) {
		this (patternContent, -1, -1);
	}
	
	public List<String> getPatternContent() {
		return this.patternContent;
	}
	
	public long getColumnIndex() {
		return this.columnIndex;
	}
	
	public void setColumnIndex(long columnIndex) {
		this.columnIndex = columnIndex;
	}
	
	public long getRowIndex() {
		return this.rowIndex;
	}
	
	public void setRowValue(long rowIndex) {
		this.rowIndex = rowIndex;
	}

}
