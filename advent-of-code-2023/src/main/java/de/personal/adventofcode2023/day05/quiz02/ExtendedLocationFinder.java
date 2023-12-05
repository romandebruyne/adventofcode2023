package de.personal.adventofcode2023.day05.quiz02;

import java.util.ArrayList;
import java.util.List;

import de.personal.adventofcode2023.day05.quiz01.LocationFinder;

public class ExtendedLocationFinder extends LocationFinder {
	
	@Override
	public void transformLines(List<String> allLines) {
		int mapId = 0;
		List<Long> tempSeedList = new ArrayList<>();
		
		for (String line : allLines) {
			if ("seeds:".equals(line.split(" ")[0])) {
				for (String seed : line.split(" ")) {
					if (this.isNumeric(seed.trim())) {
						tempSeedList.add(Long.parseLong(seed));
					}
				}
				
				for (int i = 0; i < tempSeedList.size(); i += 2) {
					for (long j = tempSeedList.get(i); j < tempSeedList.get(i) + tempSeedList.get(i + 1); j++) {
						this.getAllSeeds().add(j);
					}
				}
			} else if (!this.isNumeric(line.split(" ")[0])) {
				mapId++;
			} else if (this.isNumeric(line.split(" ")[0])) {
				this.putKeysAndValueToMap(mapId, line);
			}
		}
	}
	
}
