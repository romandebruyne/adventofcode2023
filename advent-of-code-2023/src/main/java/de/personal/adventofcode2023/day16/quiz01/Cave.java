package de.personal.adventofcode2023.day16.quiz01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cave {
	private char[][] structure;
	private List<Beam> beamsInCave;
	private List<Beam> beamsDone;

	public Cave(String inputPath, int size) {
		this.structure = CaveUtils.importCaveStructure(inputPath, size);
		this.beamsInCave = new ArrayList<>();
		this.beamsDone = new ArrayList<>();
	}
	
	public void energizeTiles() {
		int i = 0;
		Beam startingBeam = new Beam(0, -1, 'R');
		this.beamsInCave.add(startingBeam);
		
		while (i < 10000) {
			moveBeamsInCave();
			i++;
		}
	}
	
	public long getNumOfEnergizedTiles() {
		List<List<Integer>> tiles = new ArrayList<>();
		List<Integer> tile;
		
		energizeTiles();
		
		for (Beam beam : this.beamsDone) {
			tile = Arrays.asList(beam.getRow(), beam.getCol());
			if (!tiles.contains(tile)) {
				tiles.add(tile);
			}
		}
		
		return tiles.size() - 1;
	}

	public void moveBeamsInCave() {
		Beam beam, tempBeam = null;
		
		for (int i = 0; i < this.beamsInCave.size(); i++) {
			beam = this.beamsInCave.get(i);
			
			this.beamsInCave.remove(beam);
			
			if (!CaveUtils.containsBeam(this.beamsDone, beam)) {
				this.beamsDone.add(beam);
			} else {
				break;
			}

			switch (beam.getDirection()) {
			case 'U':
				tempBeam = new Beam(beam.getRow() - 1, beam.getCol(), beam.getDirection());
				break;

			case 'D':
				tempBeam = new Beam(beam.getRow() + 1, beam.getCol(), beam.getDirection());
				break;

			case 'R':
				tempBeam = new Beam(beam.getRow(), beam.getCol() + 1, beam.getDirection());
				break;

			case 'L':
				tempBeam = new Beam(beam.getRow(), beam.getCol() - 1, beam.getDirection());
				break;
			}
			
			determineNewDirection(tempBeam);
		}
	}

	public void determineNewDirection(Beam beam) {
		if (CaveUtils.beamIsInBound(this.structure, beam)) {
			char tile = this.structure[beam.getRow()][beam.getCol()];
			switch (tile) {
			case '\\':
				switch (beam.getDirection()) {
				case 'U':
					beam.setDirection('L');
					break;
				case 'D':
					beam.setDirection('R');
					break;
				case 'R':
					beam.setDirection('D');
					break;
				case 'L':
					beam.setDirection('U');
					break;
				}
				break;
			case '/':
				switch (beam.getDirection()) {
				case 'U':
					beam.setDirection('R');
					break;
				case 'D':
					beam.setDirection('L');
					break;
				case 'R':
					beam.setDirection('U');
					break;
				case 'L':
					beam.setDirection('D');
					break;
				}
				break;
			case '|':
				switch (beam.getDirection()) {
				case 'R':
					beam.setDirection('U');
					this.beamsInCave.add(new Beam(beam.getRow(), beam.getCol(), 'D'));
					break;
				case 'L':
					beam.setDirection('U');
					this.beamsInCave.add(new Beam(beam.getRow(), beam.getCol(), 'D'));
					break;
				}
				break;
			case '-':
				switch (beam.getDirection()) {
				case 'U':
					beam.setDirection('R');
					this.beamsInCave.add(new Beam(beam.getRow(), beam.getCol(), 'L'));
					break;
				case 'D':
					beam.setDirection('R');
					this.beamsInCave.add(new Beam(beam.getRow(), beam.getCol(), 'L'));
					break;
				}
				break;
			default:
				// Nothing happens.
			}
			this.beamsInCave.add(beam);
		}
	}
}
