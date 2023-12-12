package de.personal.adventofcode2023.day12.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RecordUtils {
	
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
	
	public static long calculateSumOfFeasiblePermutation(List<Record> allRecords) {
		List<String> permutedParts = new ArrayList<>();
		List<Integer> indexes = new ArrayList<>();
		String[] permutations;
		long sum = 0;
		
		for (Record rec : allRecords) {
			indexes = getIndexesOfQuestionMarks(rec.getParts());
			permutations = RecordUtils.getAllPermutations(indexes.size());
			permutedParts = RecordUtils.permuteString(rec.getParts(), indexes, permutations);
			sum += permutedParts.stream().filter(p -> RecordUtils.isFeasiblePermutation(p, rec.getSizes())).count();
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
	
	public static List<String> permuteString(String input, List<Integer> indexes, String[] permutations) {
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
	
	public static String[] getAllPermutations(int listLength) {
		String[] elements = {".", "#"}, allLists = new String[(int) Math.pow(elements.length, listLength)],
				allSublists;
		int arrayIndex;

		if (listLength == 1)
			return elements;
		else {
			allSublists = getAllPermutations(listLength - 1);
			arrayIndex = 0;

			for (int i = 0; i < elements.length; i++) {
				for (int j = 0; j < allSublists.length; j++) {
					allLists[arrayIndex] = elements[i] + allSublists[j];
					arrayIndex++;
				}
			}

			return allLists;
		}
	}
	
	public static String[] oldgetAllPermutations(String[] elements, int lengthOfList) {
		// initialize our returned list with the number of elements calculated above
		String[] allLists = new String[(int) Math.pow(elements.length, lengthOfList)];

		// lists of length 1 are just the original elements
		if (lengthOfList == 1)
			return elements;
		else {
			// the recursion--get all lists of length 3, length 2, all the way up to 1
			String[] allSublists = oldgetAllPermutations(elements, lengthOfList - 1);

			// append the sublists to each element
			int arrayIndex = 0;

			for (int i = 0; i < elements.length; i++) {
				for (int j = 0; j < allSublists.length; j++) {
					// add the newly appended combination to the list
					allLists[arrayIndex] = elements[i] + allSublists[j];
					arrayIndex++;
				}
			}

			return allLists;
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