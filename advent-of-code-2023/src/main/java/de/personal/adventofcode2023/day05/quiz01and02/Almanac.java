package de.personal.adventofcode2023.day05.quiz01and02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Almanac {
	private List<String> allLines;
	private final Map<List<Long>, List<Long>> seedToSoilMap = new HashMap<>();
	private final Map<List<Long>, List<Long>> soilToFertilizerMap = new HashMap<>();
	private final Map<List<Long>, List<Long>> fertilizerToWaterMap = new HashMap<>();
	private final Map<List<Long>, List<Long>> waterToLightMap = new HashMap<>();
	private final Map<List<Long>, List<Long>> lightToTemperatureMap = new HashMap<>();
	private final Map<List<Long>, List<Long>> temperatureToHumidityMap = new HashMap<>();
	private final Map<List<Long>, List<Long>> humidityToLocationMap = new HashMap<>();
	
	public Almanac(String inputPath) {
		this.allLines = AlmanacUtils.importAllLines(inputPath);
		AlmanacUtils.extractMappingsFromAllLines(this);
	}
	
	public List<String> getAllLines() {
		return this.allLines;
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

}
