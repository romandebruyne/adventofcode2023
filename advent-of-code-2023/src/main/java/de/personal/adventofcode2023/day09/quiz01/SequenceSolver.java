package de.personal.adventofcode2023.day09.quiz01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SequenceSolver {
	
	private SequenceSolver() {}
	
	public static long calculateSumOfPredictedValues(List<List<Long>> allSequences) {
		long sum = 0L;
		
		for (List<Long> seq : allSequences) {
			sum += predictNextValueOfSequence(findZeroSequence(seq));
		}
		
		return sum;
	}
	
	public static long predictNextValueOfSequence(List<List<Long>> seqUpToAllZeros) {
		long lastValue, lastValueFromPrevious;
		List<Long> previous = new ArrayList<>(), current = new ArrayList<>();
		
		for (int i = seqUpToAllZeros.size() - 1; i > 0; i--) {
			previous = seqUpToAllZeros.get(i - 1);
			current = seqUpToAllZeros.get(i);
			
			lastValueFromPrevious = previous.get(previous.size() - 1);
			lastValue = current.get(current.size() - 1);
			seqUpToAllZeros.get(i - 1).add(lastValue + lastValueFromPrevious);
		}
		
		return seqUpToAllZeros.get(0).get(seqUpToAllZeros.get(0).size() - 1);
	}
	
	
	public static List<List<Long>> findZeroSequence(List<Long> sequence) {
		List<List<Long>> sequences = new ArrayList<>(Arrays.asList(sequence));
		
		for (int i = 0; i < sequences.size(); i++) {
			sequences.add(new ArrayList<>());
			for (int j = 0; j < sequences.get(i).size() - 1; j++) {
				sequences.get(i + 1).add(sequences.get(i).get(j + 1) - sequences.get(i).get(j));
			}
			
			if (sequences.get(i + 1).stream().allMatch(s -> s == 0)) {
				return sequences;
			}
		}
		
		return sequences;
	}
	
	public static List<List<Long>> importAllSequences(String inputPath) {
		List<List<Long>> allLines = new ArrayList<>();
		String line;
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
			while ((line = br.readLine()) != null) {
				allLines.add(Arrays.stream(line.split(" ")).map(Long::parseLong)
						.collect(Collectors.toList()));
			}
		} catch (IOException e) {
			System.err.println("Error during data import.");
		}
		
		return allLines;
	}

}
