package de.personal.adventofcode2023.day12.quiz01and02;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordUtils {
	
	private static Map<Integer, List<String>> CACHE = new HashMap<>();
	
	private RecordUtils() {}
	
	public static List<Record> importAllData(String inputPath) {
		String line, parts;
		List<Integer> sizes;
		List<Record> allRecords = new ArrayList<>();
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			while ((line = br.readLine()) != null) {
				sizes = new ArrayList<>();
				parts = line.split(" ")[0];
				
				for (String size : line.split(" ")[1].split(",")) {
					sizes.add(Integer.parseInt(size));
				}
				
				allRecords.add(new Record(parts, sizes));
			}
			
		} catch (IOException e) {
			System.err.println("Error during data import.");
		}
		
		return allRecords;
	}
	
	public static List<Record> transformAllRecords(List<Record> allRecords, int multiplier) {
		List<Record> transformedRecords = new ArrayList<>();
		
		for (Record rec : allRecords) {
			rec.transformParts(multiplier);
			rec.transformSizes(multiplier);
			transformedRecords.add(rec);
		}
		
		return transformedRecords;
	}
	
	public static long calculateSumOfFeasiblePermutation(List<Record> allRecords) {
		Map<Integer, List<String>> permutationsMap = new HashMap<>();
		List<String> permutations, permutedParts = new ArrayList<>();
		List<Integer> indexes = new ArrayList<>();
		long sum = 0;
		
		for (Record rec : allRecords) {
			indexes = getIndexesOfQuestionMarks(rec.getParts());
			
			if (!permutationsMap.containsKey(indexes.size())) {
				permutations = getAllPermutations(indexes.size());
				permutationsMap.put(indexes.size(), permutations);
			} else {
				permutations = permutationsMap.get(indexes.size());
			}
			
			permutedParts = permuteString(rec.getParts(), indexes, permutations);
			sum += permutedParts
					.stream()
					.parallel()
					.filter(p -> isFeasiblePermutation(p, rec.getSizes())).count();
		}
		
		return sum;
	}
	
	public static boolean isFeasiblePermutation(String permutedString, List<Integer> sizes) {
		List<Integer> hashCounts = new ArrayList<>();
		int hashCount;
		
		for (int i = 0; i < permutedString.length(); i++) {
			hashCount = 0;
			
			if (permutedString.charAt(i) == '#') {
				hashCount++;
				
				if (i == permutedString.length() - 1) {
					hashCounts.add(hashCount);
					return sizes.equals(hashCounts);
				}
				
				for (int j = i + 1; j < permutedString.length(); j++) {
					if (permutedString.charAt(j) == '#') {
						hashCount++;
						if (j == permutedString.length() - 1) {
							hashCounts.add(hashCount);
							return sizes.equals(hashCounts);
						}
					} else {
						i += hashCount;
						hashCounts.add(hashCount);
						break;
					}
				}
			}
		}
		
		return sizes.equals(hashCounts);
	}
	
	public static List<String> permuteString(String input, List<Integer> indexes, List<String> permutations) {
		char[] charArray = input.toCharArray();
		List<String> allStrings = new ArrayList<>();
		
		for (String perm : permutations) {
			for (int i = 0; i < indexes.size(); i++) {
				charArray[indexes.get(i)] = perm.charAt(i);
			}
			
			allStrings.add(String.valueOf(charArray));
		}
		
		return allStrings;
	}
	
	public static List<String> getAllPermutations(int listLength) {
		List<String> elements = new ArrayList<>(Arrays.asList(".", "#")),
				allPerms = new ArrayList<>(), allPermSublists;

		if (listLength == 1)
			return elements;
		else {
			if (!CACHE.containsKey(listLength - 1)) {
				allPermSublists = getAllPermutations(listLength - 1);
				CACHE.put(listLength - 1, allPermSublists);
			} else {
				allPermSublists = CACHE.get(listLength - 1);
			}

			for (int i = 0; i < elements.size(); i++) {
				for (int j = 0; j < allPermSublists.size(); j++) {
					allPerms.add(elements.get(i) + allPermSublists.get(j));
				}
			}

			return allPerms;
		}
	}
	
	public static List<Integer> getIndexesOfQuestionMarks(String input) {
		List<Integer> indexes = new ArrayList<>();
		
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '?') {
				indexes.add(i);
			}
		}
		
		return indexes;
	}
}