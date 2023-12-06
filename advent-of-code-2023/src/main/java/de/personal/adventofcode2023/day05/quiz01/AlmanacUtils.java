package de.personal.adventofcode2023.day05.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AlmanacUtils {
	
	private AlmanacUtils() {}
	
	public static List<String> importAllLines(String inputPath) {
		List<String> allLines = new ArrayList<>();
		String line;

		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					allLines.add(line);
				}
			}

		} catch (IOException e) {
			System.err.println("Error occurred during data import.");
		}
		
		return allLines;
	}
	
	public static List<Seed> extractSeedsFromAllLines(Almanac almanac) {
		List<Seed> seeds = new ArrayList<>();

		for (String line : almanac.getAllLines()) {
			if ("seeds:".equals(line.split(" ")[0])) {
				for (String seed : line.split(" ")) {
					if (isNumeric(seed.trim())) {
						seeds.add(new Seed(Long.parseLong(seed)));
					}
				}
			}
		}
		
		return seeds;
	}
	
	public static void extractMappingsFromAllLines(Almanac almanac) {
		int mapId = 0;

		for (String line : almanac.getAllLines().subList(1, almanac.getAllLines().size() - 1)) {
			if (!isNumeric(line.split(" ")[0])) {
				mapId++;
			} else if (isNumeric(line.split(" ")[0])) {
				putKeysAndValueToMap(almanac, mapId, line);
			}
		}
	}
	
	public static void putKeysAndValueToMap(Almanac almanac, int mapId, String sourceDestinationAndRange) {
		Map<List<Long>, List<Long>> mapToFill = new HashMap<>();

		long destination = Long.parseLong(sourceDestinationAndRange.split(" ")[0].trim());
		long source = Long.parseLong(sourceDestinationAndRange.split(" ")[1].trim());
		long range = Long.parseLong(sourceDestinationAndRange.split(" ")[2].trim());

		switch (mapId) {
		case 1:
			mapToFill = almanac.getSeedToSoilMap();
			break;
		case 2:
			mapToFill = almanac.getSoilToFertilizerMap();
			break;
		case 3:
			mapToFill = almanac.getFertilizerToWaterMap();
			break;
		case 4:
			mapToFill = almanac.getWaterToLightMap();
			break;
		case 5:
			mapToFill = almanac.getLightToTemperatureMap();
			break;
		case 6:
			mapToFill = almanac.getTemperatureToHumidityMap();
			break;
		case 7:
			mapToFill = almanac.getHumidityToLocationMap();
			break;
		}

		mapToFill.put(new ArrayList<>(Arrays.asList(source, source + range + 1)),
				new ArrayList<>(Arrays.asList(destination, destination + range + 1)));
	}
	
	public static long findPositionOfValue(Map<List<Long>, List<Long>> map, long value) {
		for (Entry<List<Long>, List<Long>> entry : map.entrySet()) {
			if (value >= entry.getKey().get(0) && value <= entry.getKey().get(1)) {
				return entry.getValue().get(0) + (value - entry.getKey().get(0));
			}
		}

		return value;
	}
	
	public static long findLocationWithLowestValue(List<Seed> seeds) {
		return seeds.stream().min(Comparator.comparing(Seed::getLocationValue)).get().getLocationValue();
	}
	
	public static boolean isNumeric(String input) {
		try {
			Long.parseLong(input.trim());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
