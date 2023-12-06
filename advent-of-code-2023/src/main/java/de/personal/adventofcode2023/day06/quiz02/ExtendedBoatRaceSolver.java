package de.personal.adventofcode2023.day06.quiz02;

public class ExtendedBoatRaceSolver {
	private long time = 55999793L;
	private long distance = 401148522741405L;

	public int getPotentialWins(long time, long distance) {
		int potentialWins = 0;
		
		for (int millisecond = 1; millisecond <= time; millisecond++) {
			if (millisecond * (time - millisecond) > distance) {
				potentialWins++;
			}
		}
		
		return potentialWins;
	}
	
	public long getTime() {
		return this.time;
	}
	
	public long getDistance() {
		return this.distance;
	}
}
