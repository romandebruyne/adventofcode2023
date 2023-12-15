package de.personal.adventofcode2023.day15.quiz01and02;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public static List<Lens> transformDataToLenses(List<String> allData) {
		List<Lens> allLenses = new ArrayList<>();
		String label;
		char operator;
		long focalLength;
		
		for (String data: allData) {
			if (data.contains("=")) {
				label = data.substring(0, data.indexOf("="));
				operator = '=';
				focalLength = Long.parseLong(data.substring(data.indexOf("=") + 1));
			} else {
				label = data.substring(0, data.indexOf("-"));
				operator = '-';
				focalLength = -1L;
			}
			
			allLenses.add(new Lens(label, operator, focalLength));
		}
		
		return allLenses;
	}
	
	public static Map<Long, List<Lens>> putLensesIntoBoxes(List<Lens> allLenses) {
		Map<Long, List<Lens>> boxes = createBoxes();
		List<Lens> box = new ArrayList<>();
			
		for (Lens lens : allLenses) {
			box = boxes.get(lens.getHashValue());
			
			if (lens.getOperator() == '-') {
				if (boxContainsLensWithSameLabel(box, lens)) {
					box = removeLensWithSameLabel(box, lens);
				}
				
			} else {
				if (boxContainsLensWithSameLabel(box, lens)) {
					box = replaceLensWithSameLabel(box, lens);
				} else {
					box.add(lens);
				}
			}
			
			boxes.put(lens.getHashValue(), box);
		}
		
		return boxes;
	}
	
	private static Map<Long, List<Lens>> createBoxes() {
		Map<Long, List<Lens>> boxes = new HashMap<>();
		
		for (long i = 0; i < 256; i++) {
			boxes.put(i, new ArrayList<>());
		}
		
		return boxes; 
	}
	
	public static boolean boxContainsLensWithSameLabel(List<Lens> box, Lens lens) {
		return box.stream().anyMatch(l -> l.getLabel().equals(lens.getLabel()));
	}
	
	public static List<Lens> removeLensWithSameLabel(List<Lens> box, Lens lens) {
		for (Lens lensInBox : box) {
			if (lensInBox.getLabel().equals(lens.getLabel())) {
				box.remove(lensInBox);
				return box;
			}
		}
		
		return box;
	}
	
	public static List<Lens> replaceLensWithSameLabel(List<Lens> box, Lens lens) {
		int index = 0;
		
		for (Lens lensInBox : box) {
			if (lensInBox.getLabel().equals(lens.getLabel())) {
				box.set(index, lens);
				return box;
			}
			index++;
		}
		
		return box;
	}
	
	public static long applyHashAlgorithm(String input) {
		long value = 0;
		
		for (char c : input.toCharArray()) {
			value += c;
			value = (value * 17) % 256;
		}
		
		return value;
	}
	
	public static long calculateSumPartOne(List<String> allData) {
		long sum = 0L;
		
		for (String str : allData) {
			sum += applyHashAlgorithm(str);
		}
		
		return sum;
	}
	
	public static long calculateSumPartTwo(List<String> allData) {
		Map<Long, List<Lens>> boxes = putLensesIntoBoxes(transformDataToLenses(allData));
		long sum = 0L;
		
		for (long key : boxes.keySet()) {
			for (Lens lens : boxes.get(key)) {
				sum += ((key + 1) * (boxes.get(key).indexOf(lens) + 1) * lens.getFocalLength());
			}
		}
		
		return sum;
	}
}
