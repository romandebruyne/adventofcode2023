package de.personal.adventofcode2023.day15.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HashAlgoSolver {
	
	private HashAlgoSolver() {}
	
	public static List<String> importAllData(String inputPath) {
		List<String> allData = new ArrayList<>();
		String line;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			while ((line = br.readLine()) != null) {
				allData.addAll(Arrays.asList(line.split(",")));
			}
		} catch (IOException e) {
			System.err.println("Error during data import.");
		}
		
		return allData;
	}
	
	public static long applyHashAlgorithm(String input) {
		long value = 0;
		
		for (char c : input.toCharArray()) {
			value += c;
			value = (value * 17) % 256;
		}
		
		return value;
	}
	
	public static long calculateSum(List<String> allData) {
		long sum = 0L;
		
		for (String str : allData) {
			sum += applyHashAlgorithm(str);
		}
		
		return sum;
	}
}
