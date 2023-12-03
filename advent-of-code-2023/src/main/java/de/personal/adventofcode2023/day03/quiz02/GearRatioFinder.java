package de.personal.adventofcode2023.day03.quiz02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import de.personal.adventofcode2023.day03.quiz01.PartNumberFinder;

public class GearRatioFinder extends PartNumberFinder {
	private final Map<String, List<Integer>> allGears = new HashMap<>();

	public int calculateSumOfGearRatios(Map<String, List<Integer>> allGears) {
		int sumOfGearRatios = 0;
		
		for (Entry<String, List<Integer>> gear : allGears.entrySet()) {
			if (gear.getValue().size() > 1) {
				sumOfGearRatios += gear.getValue().get(0) * gear.getValue().get(1);
			}
		}
		
		return sumOfGearRatios;
	}

	public void findAllGears(List<String> allLines) {
		int partNumber;
		List<Integer> numberIndexPositions = new ArrayList<>();
		
		for (int lineIndex = 0; lineIndex < allLines.size(); lineIndex++) {
			for (int i = 0; i < allLines.get(lineIndex).length(); i++) {
				partNumber = this.getPartNumber(allLines.get(lineIndex), i);
				numberIndexPositions = this.getPartNumberIndexPositions(allLines.get(lineIndex), i);
				
				for (String position : getAsteriskPosition(allLines, lineIndex, numberIndexPositions)) {
					System.out.println(position);
					if (this.allGears.containsKey(position)) {
						this.allGears.get(position).add(partNumber);
					} else {
						this.allGears.put(position, new ArrayList<>(Arrays.asList(partNumber)));
					}
				}
				
				i += numberIndexPositions.size();
			}
		}
	}

	public Set<String> getAsteriskPosition(List<String> allLines, int lineIndex,
			List<Integer> numberIndexPositions) {
		Set<String> asterikPositions = new HashSet<>();

		for (int position : numberIndexPositions) {
			if (lineIndex > 0) {
				if (position == 0) {
					if (isAsterisk(allLines.get(lineIndex - 1).charAt(position))) {
						asterikPositions.add(lineIndex - 1 + ", " + position);
					} else if (isAsterisk(allLines.get(lineIndex - 1).charAt(position + 1))) {
						asterikPositions.add(lineIndex - 1 + ", " + (position + 1));
					}
				} else if (position == allLines.get(lineIndex).length() - 1) {
					if (isAsterisk(allLines.get(lineIndex - 1).charAt(position - 1))) {
						asterikPositions.add(lineIndex - 1 + ", " + (position - 1));
					} else if (isAsterisk(allLines.get(lineIndex - 1).charAt(position))) {
						asterikPositions.add(lineIndex - 1 + ", " + position);
					}
				} else {
					if (isAsterisk(allLines.get(lineIndex - 1).charAt(position - 1))) {
						asterikPositions.add(lineIndex - 1 + ", " + (position - 1));
					} else if (isAsterisk(allLines.get(lineIndex - 1).charAt(position))) {
						asterikPositions.add(lineIndex - 1 + ", " + position);
					} else if (isAsterisk(allLines.get(lineIndex - 1).charAt(position + 1))) {
						asterikPositions.add(lineIndex - 1 + ", " + (position + 1));
					}
				}
			}
			
			if (lineIndex < allLines.size() - 1) {
				if (position == 0) {
					if (isAsterisk(allLines.get(lineIndex + 1).charAt(position))) {
						asterikPositions.add(lineIndex + 1 + ", " + position);
					} else if (isAsterisk(allLines.get(lineIndex + 1).charAt(position + 1))) {
						asterikPositions.add(lineIndex + 1 + ", " + (position + 1));
					}
					
				} else if (position == allLines.get(lineIndex + 1).length() - 1) {
					if (isAsterisk(allLines.get(lineIndex + 1).charAt(position - 1))) {
						asterikPositions.add(lineIndex + 1 + ", " + (position - 1));
					} else if (isAsterisk(allLines.get(lineIndex + 1).charAt(position))) {
						asterikPositions.add(lineIndex + 1 + ", " + position);
					}
				} else {
					if (isAsterisk(allLines.get(lineIndex + 1).charAt(position - 1))) {
						asterikPositions.add(lineIndex + 1 + ", " + (position - 1));
					} else if (isAsterisk(allLines.get(lineIndex + 1).charAt(position))) {
						asterikPositions.add(lineIndex + 1 + ", " + position);
					} else if (isAsterisk(allLines.get(lineIndex + 1).charAt(position + 1))) {
						asterikPositions.add(lineIndex + 1 + ", " + (position + 1));
					}
				}
			}
			
			if (position == 0) {
				if (isAsterisk(allLines.get(lineIndex).charAt(position + 1))) {
					asterikPositions.add(lineIndex + ", " + (position + 1));
				}
			} else if (position == allLines.get(lineIndex).length() - 1) {
				if (isAsterisk(allLines.get(lineIndex).charAt(position - 1))) {
					asterikPositions.add(lineIndex + ", " + (position - 1));
				}
			} else {
				if (isAsterisk(allLines.get(lineIndex).charAt(position - 1))) {
					asterikPositions.add(lineIndex + ", " + (position - 1));
				} else if (isAsterisk(allLines.get(lineIndex).charAt(position + 1))) {
					asterikPositions.add(lineIndex + ", " + (position + 1));
				}
			}
		}

		return asterikPositions;
	}
	
	public int getPartNumberLeftFromAsterik(String input, int startPosition) {
		String partNumberAsStringReversedOrder = "", partNumberAsString = "";
		
		for (int i = startPosition; i >= 0; i--) {
			if (isNumeric(input.charAt(i))) {
				partNumberAsStringReversedOrder += input.charAt(i);
			} else if (isAsterisk(input.charAt(i)) || input.charAt(i) == '.') {
				break;
			}
		}
		
		for (int i = partNumberAsStringReversedOrder.length() - 1; i >= 0; i--) {
			partNumberAsString += partNumberAsStringReversedOrder.split("")[i];
		}
		
		try {
			return Integer.parseInt(partNumberAsString);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public int getPartNumberRightFromAsterik(String input, int startPosition) {
		String partNumberAsString = "";
		
		for (int i = startPosition; i < input.length(); i++) {
			if (isNumeric(input.charAt(i))) {
				partNumberAsString += input.charAt(i);
			} else if (isAsterisk(input.charAt(i)) || input.charAt(i) == '.') {
				break;
			}
		}
		
		try {
			return Integer.parseInt(partNumberAsString);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private boolean isAsterisk(char input) {
		return input == '*';
	}

	public Map<String, List<Integer>> getAllGears() {
		return this.allGears;
	}
}
