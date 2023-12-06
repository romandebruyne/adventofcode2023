package de.personal.adventofcode2023.day05.quiz01and02;

public class Seed {
	
	private long sourceValue;
	private long locationValue;
	private long sourceRangeLength;
	
	public Seed(long sourceValue, long sourceRangeLength) {
		this.sourceValue = sourceValue;
		this.sourceRangeLength = sourceRangeLength;
	}
	
	public Seed(long sourceValue) {
		this.sourceValue = sourceValue;
	}
	
	public long determineLocationValue(Almanac almanac) {
		long soil, fertilizer, water, light, temperature, humidity;
		
		soil = AlmanacUtils.findPositionOfValue(almanac.getSeedToSoilMap(), this.sourceValue);
		fertilizer = AlmanacUtils.findPositionOfValue(almanac.getSoilToFertilizerMap(), soil);
		water = AlmanacUtils.findPositionOfValue(almanac.getFertilizerToWaterMap(), fertilizer);
		light = AlmanacUtils.findPositionOfValue(almanac.getWaterToLightMap(), water);
		temperature = AlmanacUtils.findPositionOfValue(almanac.getLightToTemperatureMap(), light);
		humidity = AlmanacUtils.findPositionOfValue(almanac.getTemperatureToHumidityMap(), temperature);
		this.locationValue = AlmanacUtils.findPositionOfValue(almanac.getHumidityToLocationMap(), humidity);
		return this.locationValue;
	}

	public long getSourceValue() {
		return this.sourceValue;
	}

	public long getLocationValue() {
		return this.locationValue;
	}

	public long getSourceRangeLength() {
		return this.sourceRangeLength;
	}
}
