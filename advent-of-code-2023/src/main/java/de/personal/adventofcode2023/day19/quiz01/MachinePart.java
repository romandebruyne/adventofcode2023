package de.personal.adventofcode2023.day19.quiz01;

import java.util.Map;

public class MachinePart {
	
	private Map<String, Long> ratings;
	private boolean isAccepted;
	
	public MachinePart(Map<String, Long> ratings) {
		this.ratings = ratings;
	}

	public Map<String, Long> getRatings() {
		return this.ratings;
	}
	
	public boolean isAccepted() {
		return this.isAccepted;
	}
	
	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
}
