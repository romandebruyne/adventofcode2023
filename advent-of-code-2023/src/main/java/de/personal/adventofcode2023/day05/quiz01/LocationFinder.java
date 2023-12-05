package de.personal.adventofcode2023.day05.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LocationFinder {
	private final List<String> allLines = new ArrayList<>();
	private final List<Long> allSeeds = new ArrayList<>();
	private final Map<List<Long>, List<Long>> seedToSoilMap = new HashMap<>();
	private final Map<List<Long>, List<Long>> soilToFertilizerMap = new HashMap<>();
	private final Map<List<Long>, List<Long>> fertilizerToWaterMap = new HashMap<>();
	private final Map<List<Long>, List<Long>> waterToLightMap = new HashMap<>();
	private final Map<List<Long>, List<Long>> lightToTemperatureMap = new HashMap<>();
	private final Map<List<Long>, List<Long>> temperatureToHumidityMap = new HashMap<>();
	private final Map<List<Long>, List<Long>> humidityToLocationMap = new HashMap<>();
	private final Map<Long, Long> seedToLocation = new HashMap<>();

	public long findLocationWithLowestValue(Map<Long, Long> seedToLocationMap) {
		long lowestValue = Long.MAX_VALUE;

		for (long value : seedToLocationMap.values()) {
			if (value < lowestValue) {
				lowestValue = value;
			}
		}

		return lowestValue;
	}

	public void findLocations() {
		long location = 0, count = 1;

		for (long seed : this.allSeeds) {
			location = seed;
			System.out.println(count + ": " + seed);
			location = findPositionOfValue(this.seedToSoilMap, location);
			location = findPositionOfValue(this.soilToFertilizerMap, location);
			location = findPositionOfValue(this.fertilizerToWaterMap, location);
			location = findPositionOfValue(this.waterToLightMap, location);
			location = findPositionOfValue(this.lightToTemperatureMap, location);
			location = findPositionOfValue(this.temperatureToHumidityMap, location);
			location = findPositionOfValue(this.humidityToLocationMap, location);

			this.seedToLocation.put(seed, location);
		}
	}

	public long findPositionOfValue(Map<List<Long>, List<Long>> map, long value) {
		for (Entry<List<Long>, List<Long>> entry : map.entrySet()) {
			if (value >= entry.getKey().get(0) && value <= entry.getKey().get(1)) {
				return entry.getValue().get(0) + (value - entry.getKey().get(0));
			}
		}

		return value;
	}

	public void importAllLines(String inputPath) {
		String line;

		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					this.allLines.add(line);
				}
			}

		} catch (IOException e) {
			System.err.println("Error occurred during data import.");
		}
	}

	public void transformLines(List<String> allLines) {
		int mapId = 0;

		for (String line : allLines) {
			if ("seeds:".equals(line.split(" ")[0])) {
				for (String seed : line.split(" ")) {
					if (isNumeric(seed.trim())) {
						this.allSeeds.add(Long.parseLong(seed));
					}
				}
			} else if (!isNumeric(line.split(" ")[0])) {
				mapId++;
			} else if (isNumeric(line.split(" ")[0])) {
				putKeysAndValueToMap(mapId, line);
			}
		}
	}

	public void putKeysAndValueToMap(int mapId, String sourceDestinationAndRange) {
		Map<List<Long>, List<Long>> mapToFill = new HashMap<>();

		long destination = Long.parseLong(sourceDestinationAndRange.split(" ")[0].trim());
		long source = Long.parseLong(sourceDestinationAndRange.split(" ")[1].trim());
		long range = Long.parseLong(sourceDestinationAndRange.split(" ")[2].trim());

		switch (mapId) {
		case 1:
			mapToFill = this.seedToSoilMap;
			break;
		case 2:
			mapToFill = this.soilToFertilizerMap;
			break;
		case 3:
			mapToFill = this.fertilizerToWaterMap;
			break;
		case 4:
			mapToFill = this.waterToLightMap;
			break;
		case 5:
			mapToFill = this.lightToTemperatureMap;
			break;
		case 6:
			mapToFill = this.temperatureToHumidityMap;
			break;
		case 7:
			mapToFill = this.humidityToLocationMap;
			break;
		}

		mapToFill.put(new ArrayList<>(Arrays.asList(source, source + range - 1)),
				new ArrayList<>(Arrays.asList(destination, destination + range - 1)));
	}

	public boolean isNumeric(String input) {
		try {
			Long.parseLong(input.trim());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public List<String> getAllLines() {
		return this.allLines;
	}

	public List<Long> getAllSeeds() {
		return this.allSeeds;
	}

	public Map<List<Long>, List<Long>> getSeedToSoilMap() {
		return this.seedToSoilMap;
	}

	public Map<List<Long>, List<Long>> getSoilToFertilizerMap() {
		return this.soilToFertilizerMap;
	}

	public Map<List<Long>, List<Long>> getFertilizerToWaterMap() {
		return this.fertilizerToWaterMap;
	}

	public Map<List<Long>, List<Long>> getWaterToLightMap() {
		return this.waterToLightMap;
	}

	public Map<List<Long>, List<Long>> getLightToTemperatureMap() {
		return this.lightToTemperatureMap;
	}

	public Map<List<Long>, List<Long>> getTemperatureToHumidityMap() {
		return this.temperatureToHumidityMap;
	}

	public Map<List<Long>, List<Long>> getHumidityToLocationMap() {
		return this.humidityToLocationMap;
	}

	public Map<Long, Long> getSeedToLocationMap() {
		return this.seedToLocation;
	}
}
